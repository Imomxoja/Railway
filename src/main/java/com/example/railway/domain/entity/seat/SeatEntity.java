package com.example.railway.domain.entity.seat;

import com.example.railway.domain.entity.BaseEntity;
import com.example.railway.domain.entity.ticket.TicketEntity;
import com.example.railway.domain.entity.train.TrainCarriageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "seats")
public class SeatEntity extends BaseEntity {
    @Column(name = "seat_number")
    private Integer seatNumber;
    private Boolean isBooked;

    @OneToMany(mappedBy = "seats")
    private List<TicketEntity> tickets;

    @ManyToOne
    @JoinColumn(name = "train_carriage_id")
    private TrainCarriageEntity trainCarriage;


}
