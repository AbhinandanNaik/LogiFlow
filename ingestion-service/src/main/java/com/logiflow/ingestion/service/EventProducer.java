package com.logiflow.ingestion.service;

import com.logiflow.ingestion.config.RabbitMQConfig;
import com.logiflow.ingestion.model.GpsCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProducer {

    private final RabbitTemplate rabbitTemplate;

    public Mono<Void> publishGpsEvent(GpsCoordinates coordinates) {
        return Mono.fromRunnable(() -> {
            log.info("Publishing event for device: {}", coordinates.deviceId());
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "gps.event." + coordinates.deviceId(),
                coordinates
            );
        });
    }
}
