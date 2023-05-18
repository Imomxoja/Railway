package com.example.railway.domain.entity.user;


import com.example.railway.domain.entity.BaseEntity;
import com.example.railway.domain.entity.ticket.TicketEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserEntity extends BaseEntity {
    private String name;

    private String email;

    private String password;

    private Double balance = 10000.0;

    private String role;

    @OneToMany(mappedBy = "users")
    private List<TicketEntity> tickets;
}
