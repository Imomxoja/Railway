package com.example.railway.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private Double balance;
    private String password;
    private String role;
    private LocalDateTime created;
    private LocalDateTime updated;
}
