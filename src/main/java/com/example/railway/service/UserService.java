package com.example.railway.service;

import com.example.railway.dao.UserDao;
import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.UserRequest;
import com.example.railway.domain.dto.response.UserResponse;
import com.example.railway.domain.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRequest, BaseResponse<UserResponse>> {
    private final ModelMapper mapper;
    private final UserDao dao;

    @Override
    public BaseResponse<UserResponse> create(UserRequest userRequest) {
        Optional<UserEntity> user = dao.findUserEntitiesByEmail(userRequest.getEmail());
        if (!userRequest.getPassword().isBlank() &&
                !userRequest.getName().isBlank() &&
                user.isEmpty() &&
                (userRequest.getRole().equals("ADMIN") || userRequest.getRole().equals("USER"))) {
            UserEntity map = mapper.map(userRequest, UserEntity.class);

            dao.save(map);

            return BaseResponse.<UserResponse>builder()
                    .status(200)
                    .message(map.getName() + " successfully saved")
                    .data(mapper.map(map, UserResponse.class))
                    .build();
        }
        if (userRequest.getName().isBlank()) {
            return BaseResponse.<UserResponse>builder()
                    .status(401)
                    .message("Username is blank")
                    .build();
        }
        else if (user.isPresent()) {
            return BaseResponse.<UserResponse>builder()
                    .status(401)
                    .message("User already registered")
                    .build();
        }
        else if (userRequest.getPassword().isBlank()) {
            return BaseResponse.<UserResponse>builder()
                    .status(401)
                    .message("password is blank")
                    .build();
        } else if (!userRequest.getRole().equals("ADMIN") && !userRequest.getRole().equals("USER")) {
            return BaseResponse.<UserResponse>builder()
                    .status(401)
                    .message("User role required")
                    .data(new UserResponse().setRole(userRequest.getRole()))
                    .build();
        }
        return BaseResponse.<UserResponse>builder()
                .build();
    }

    @Override
    public BaseResponse<UserResponse> getById(UUID id) {
        return BaseResponse.<UserResponse>builder()
                .data(mapper.map(dao.findById(id).get(), UserResponse.class))
                .build();
    }

    @Override
    public BaseResponse<UserResponse> deleteById(UUID id) {
        return null;
    }

    public BaseResponse<UserResponse> login(String email, String password) {
        Optional<UserEntity> user = dao.findUserEntitiesByEmail(email);

        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            if (Objects.equals(userEntity.getPassword(), password) && !password.isBlank()) {
                return BaseResponse.<UserResponse>builder()
                        .status(200)
                        .message(userEntity.getName() + " successfully signed")
                        .data(mapper.map(userEntity, UserResponse.class))
                        .build();
            }
        }

        return BaseResponse.<UserResponse>builder()
                .status(401)
                .message("password is blank or email/password not found")
                .build();
    }

    public BaseResponse<UserResponse> updateBalance(UUID users, Double price) {
        UserResponse data = getById(users).getData();

        if (data == null) {
            BaseResponse.<UserResponse>builder()
                    .status(401)
                    .message("User not found");
        }

        data.setBalance(data.getBalance() - price);
        dao.save(mapper.map(data, UserEntity.class));

        return BaseResponse.<UserResponse>builder()
                .status(200)
                .message("Successfully updated").build();
    }
}
