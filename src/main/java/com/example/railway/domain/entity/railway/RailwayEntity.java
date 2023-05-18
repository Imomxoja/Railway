package com.example.railway.domain.entity.railway;

import com.example.railway.domain.entity.BaseEntity;
import com.example.railway.domain.entity.train.TrainCarriageEntity;
import com.example.railway.domain.enums.CarriageType;
import com.example.railway.domain.enums.City;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "railways")
public class RailwayEntity extends BaseEntity {
    private City fromCity;
    private City toCity;
    private Double price;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String railwayName;
    @Enumerated(EnumType.STRING)
    private CarriageType carriageType;

    @OneToMany(mappedBy = "railways")
    private List<TrainCarriageEntity> trainCarriages;
}
