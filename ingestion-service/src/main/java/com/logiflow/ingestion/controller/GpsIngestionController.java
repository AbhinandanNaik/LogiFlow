package com.logiflow.ingestion.controller;

import com.logiflow.ingestion.model.GpsCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/ingestion")
@RequiredArgsConstructor
@Slf4j
public class GpsIngestionController {

    private final com.logiflow.ingestion.service.EventProducer eventProducer;

    @PostMapping("/gps")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> ingestGpsData(@RequestBody GpsCoordinates coordinates) {
        return eventProducer.publishGpsEvent(coordinates);
    }
}
