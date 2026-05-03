package com.leetcode.medium;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class GPS {

    private String eventId;
    private String vehicleId;
    private Timestamp timestamp;
    private Float lat;
    private Float lon;

}

public class InMemoryDeDuplication {

    public static void main(String[] args) {
        List<GPS> gpsData = List.of(
                GPS.builder().eventId("e1").vehicleId("v1").timestamp(Timestamp.valueOf("2024-01-01 10:00:00")).lat(40.7128f).lon(-74.0060f).build(),
                GPS.builder().eventId("e2").vehicleId("v1").timestamp(Timestamp.valueOf("2024-01-01 10:05:00")).lat(40.7128f).lon(-74.0060f).build(),
                GPS.builder().eventId("e3").vehicleId("v1").timestamp(Timestamp.valueOf("2024-01-01 10:12:00")).lat(40.7128f).lon(-74.0060f).build(),
                GPS.builder().eventId("e4").vehicleId("v2").timestamp(Timestamp.valueOf("2024-01-01 10:06:00")).lat(34.0522f).lon(-118.2437f).build(),
                GPS.builder().eventId("e5").vehicleId("v2").timestamp(Timestamp.valueOf("2024-01-01 10:14:00")).lat(34.0522f).lon(-118.2437f).build()
        );
        
        LinkedHashMap<String, List<GPS>> uniqueGpsData = new LinkedHashMap<>();

        gpsData.forEach(gps -> {
            String key = String.format("%s:%s:%s", gps.getVehicleId(), gps.getLat(), gps.getLon());
            if (uniqueGpsData.containsKey(key)) {
                List<GPS> existingGpsList = uniqueGpsData.get(key);
                GPS previousGPS = existingGpsList.get(existingGpsList.size() - 1);
                if (gps.getTimestamp().toInstant().toEpochMilli() - previousGPS.getTimestamp().toInstant().toEpochMilli() >= 600000) {
                    existingGpsList.add(gps);
                }
            } else {
                uniqueGpsData.computeIfAbsent(key, k -> new ArrayList<>()).add(gps);
            }
        });

        uniqueGpsData.forEach((key, gpsList) -> {
            gpsList.forEach(gps -> {
                System.out.println("Unique GPS Event: " + gps.getEventId() + " for Vehicle: " + gps.getVehicleId() + " at " + gps.getTimestamp() + " with coordinates (" + gps.getLat() + ", " + gps.getLon() + ")");
            });
        });
    }

}
