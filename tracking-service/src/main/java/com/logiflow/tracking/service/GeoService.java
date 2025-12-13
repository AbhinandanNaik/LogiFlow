package com.logiflow.tracking.service;

import com.logiflow.tracking.model.GpsCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.geo.Point;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoService {

    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    private static final String GEO_KEY = "logiflow:fleet:locations";

    public void updateLocation(GpsCoordinates coordinates) {
        log.info("Updating location for device: {}", coordinates.deviceId());
        redisTemplate.opsForGeo().add(
            GEO_KEY,
            new Point(coordinates.longitude(), coordinates.latitude()),
            coordinates.deviceId()
        );
        
        // Push update to WebSocket clients
        messagingTemplate.convertAndSend("/topic/tracking", coordinates);
    }
}
