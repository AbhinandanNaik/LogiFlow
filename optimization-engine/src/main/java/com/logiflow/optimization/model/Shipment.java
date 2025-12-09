package com.logiflow.optimization.model;

import lombok.Data;

@Data
public class Shipment {
    private Long id;
    private String trackingNumber;
    private String origin;
    private String destination;
    private String status;
}
