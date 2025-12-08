package com.logiflow.shipment.controller;

import com.logiflow.shipment.model.Shipment;
import com.logiflow.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment createShipment(@RequestBody Shipment shipment) {
        shipment.setTrackingNumber(UUID.randomUUID().toString());
        shipment.setCreatedAt(Instant.now());
        return shipmentRepository.save(shipment);
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @GetMapping("/{trackingNumber}")
    public Shipment getShipment(@PathVariable String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }
}
