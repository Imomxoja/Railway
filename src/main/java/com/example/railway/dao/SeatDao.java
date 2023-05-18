package com.example.railway.dao;

import com.example.railway.domain.entity.seat.SeatEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SeatDao extends JpaRepository<SeatEntity, UUID> {
    @Query("select s from seats s where s.isBooked = false and s.trainCarriage.id = :carriageId")
    List<SeatEntity> findSeatEntityByTrainCarriageId (@Param("carriageId") UUID id);

    @Modifying
    @Transactional
    @Query("update seats s set s.isBooked = true where s.id = :id")
    void updateSeatIsBooked(@Param(value = "id") UUID id);
}
