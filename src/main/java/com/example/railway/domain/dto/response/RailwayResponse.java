package com.example.railway.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RailwayResponse {
    private UUID id;
    private String fromCity;
    private String toCity;
    private Double price;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String railwayName;
    private String carriageType;
    private LocalDateTime created;
    private LocalDateTime updated;
}
