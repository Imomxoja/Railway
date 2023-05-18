package com.example.railway.dao;

import com.example.railway.domain.entity.train.TrainCarriageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TrainCarriageDao extends JpaRepository<TrainCarriageEntity, UUID> {
    @Query("select c from train_carriages c where c.railways.id = :railwayId")
    List<TrainCarriageEntity> findCarriagesByRailwayId (@Param("railwayId") UUID id);
}