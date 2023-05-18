package com.example.railway.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CarriageResponse {
    private UUID id;
    private UUID railways;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String carriageType;
    private Integer carriageNumber;
}
