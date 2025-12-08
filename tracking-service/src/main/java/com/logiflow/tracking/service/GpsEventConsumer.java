package com.logiflow.tracking.service;

import com.logiflow.tracking.config.RabbitMQConfig;
import com.logiflow.tracking.model.GpsCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GpsEventConsumer {

    private final GeoService geoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeGpsEvent(GpsCoordinates coordinates) {
        log.info("Consumed event for device: {}", coordinates.deviceId());
        geoService.updateLocation(coordinates);
    }
}
