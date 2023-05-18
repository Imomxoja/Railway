package com.example.railway.dao;

import com.example.railway.domain.entity.railway.RailwayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RailwayDao extends JpaRepository<RailwayEntity, UUID> {
   Optional<RailwayEntity> findByRailwayName (String railwayName);
}
