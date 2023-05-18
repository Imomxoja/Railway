package com.example.railway.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SeatResponse {
    private UUID id;
    private Boolean isBooked;
    private Integer seatNumber;
    private UUID trainCarriage;
    private LocalDateTime created;
    private LocalDateTime updated;
}
