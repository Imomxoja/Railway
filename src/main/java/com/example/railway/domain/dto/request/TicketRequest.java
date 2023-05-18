package com.example.railway.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TicketRequest {
    private UUID users;
    private UUID seats;
    private String cityFrom;
    private String cityTo;
    private LocalDate departureDate;
    private Double price;
}
