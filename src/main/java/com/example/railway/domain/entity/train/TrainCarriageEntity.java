package com.example.railway.domain.entity.train;

import com.example.railway.domain.entity.BaseEntity;
import com.example.railway.domain.entity.railway.RailwayEntity;
import com.example.railway.domain.entity.seat.SeatEntity;
import com.example.railway.domain.enums.CarriageType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "train_carriages")
public class TrainCarriageEntity extends BaseEntity {
    private Integer carriageNumber;
    @Enumerated(EnumType.STRING)
    private CarriageType carriageType;

    @ManyToOne
    @JoinColumn(name = "railway_id")
    private RailwayEntity railways;

    @OneToMany(mappedBy = "trainCarriage")
    private List<SeatEntity> seats;
}
