package com.example.railway.domain.entity.ticket;

import com.example.railway.domain.entity.BaseEntity;
import com.example.railway.domain.entity.seat.SeatEntity;
import com.example.railway.domain.entity.user.UserEntity;
import com.example.railway.domain.enums.City;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tickets")
public class TicketEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "seats_id")
    private SeatEntity seats;
    @Enumerated(EnumType.STRING)
    private City cityFrom;
    @Enumerated(EnumType.STRING)
    private City cityTo;
    private Double price;
    private LocalDate departureDate;
}
