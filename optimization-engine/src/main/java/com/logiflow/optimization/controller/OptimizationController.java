package com.logiflow.optimization.controller;

import com.logiflow.optimization.service.OptimizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/optimization")
@RequiredArgsConstructor
public class OptimizationController {

    private final OptimizationService optimizationService;

    @GetMapping("/eta/{trackingNumber}")
    public Instant getEta(@PathVariable String trackingNumber) {
        return optimizationService.calculateEta(trackingNumber);
    }
}
