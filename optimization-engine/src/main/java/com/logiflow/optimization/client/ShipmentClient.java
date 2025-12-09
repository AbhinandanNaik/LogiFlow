package com.logiflow.optimization.client;

import com.logiflow.optimization.model.Shipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shipment-service")
public interface ShipmentClient {

    @GetMapping("/api/v1/shipments/{trackingNumber}")
    Shipment getShipment(@PathVariable("trackingNumber") String trackingNumber);
}
