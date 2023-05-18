package com.example.railway.domain.dto.response;

import com.example.railway.domain.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private UUID id;
    private UUID users;
    private UUID seats;
    private City cityFrom;
    private City cityTo;
    private Double price;
    private LocalDate departureDate;
    private LocalDateTime created;
    private LocalDateTime updated;
}
