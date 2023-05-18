package com.example.railway.dao;

import com.example.railway.domain.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketDao extends JpaRepository<TicketEntity, UUID> {
}
