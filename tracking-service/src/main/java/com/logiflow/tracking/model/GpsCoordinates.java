package com.logiflow.tracking.model;

import java.time.Instant;

public record GpsCoordinates(
    String deviceId,
    double latitude,
    double longitude,
    Instant timestamp
) {}
