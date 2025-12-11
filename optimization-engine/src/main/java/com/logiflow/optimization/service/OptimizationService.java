package com.logiflow.optimization.service;

import com.logiflow.optimization.client.ShipmentClient;
import com.logiflow.optimization.model.Shipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptimizationService {

    private final ShipmentClient shipmentClient;
    private final Random random = new Random();

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "shipmentService", fallbackMethod = "calculateEtaFallback")
    public Instant calculateEta(String trackingNumber) {
        log.info("Calculating ETA for shipment: {}", trackingNumber);
        Shipment shipment = shipmentClient.getShipment(trackingNumber);
        
        // Simulate complex AI logic based on traffic/weather
        int days = random.nextInt(5) + 1; // 1 to 5 days
        return Instant.now().plus(days, ChronoUnit.DAYS);
    }

    public Instant calculateEtaFallback(String trackingNumber, Throwable t) {
        log.warn("Shipment Service is down, returning default ETA. Error: {}", t.getMessage());
        return Instant.now().plus(7, ChronoUnit.DAYS); // Default conservative ETA
    }
}
