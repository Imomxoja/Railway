package com.example.railway.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {
    private Integer seatNumber;
    private UUID trainCarriageId;
    private Boolean isBooked;
}
