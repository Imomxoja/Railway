package com.example.railway.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RailwayRequest {
   private String railwayName;
   private String fromCity;
   private String toCity;
   private LocalDate departureDate;
   private LocalTime departureTime;
   private LocalTime arrivalTime;
   private Double price;
   private String carriageType;
}
