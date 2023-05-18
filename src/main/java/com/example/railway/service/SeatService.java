package com.example.railway.service;

import com.example.railway.dao.SeatDao;
import com.example.railway.dao.TrainCarriageDao;
import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.SeatRequest;
import com.example.railway.domain.dto.response.SeatResponse;
import com.example.railway.domain.entity.seat.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService implements BaseService<SeatRequest, BaseResponse<SeatResponse>> {
    private final ModelMapper mapper;
    private final TrainCarriageDao trainCarriageDao;
    private final SeatDao dao;

    @Override
    public BaseResponse<SeatResponse> create(SeatRequest seatRequest) {
        SeatEntity save = mapper.map(seatRequest, SeatEntity.class);
        save.setTrainCarriage(trainCarriageDao.findById(seatRequest.getTrainCarriageId()).get());

        dao.save(save);
        return BaseResponse.<SeatResponse>builder()
                .status(200)
                .message("Successfully place added")
                .build();
    }

    @Override
    public BaseResponse<SeatResponse> getById(UUID id) {
        SeatEntity seatEntity = dao.findById(id).get();
        return BaseResponse.<SeatResponse>builder()
                .data(new SeatResponse()
                        .setCreated(seatEntity.getCreated())
                        .setUpdated(seatEntity.getUpdated())
                        .setSeatNumber(seatEntity.getSeatNumber())
                        .setId(seatEntity.getId())
                        .setIsBooked(seatEntity.getIsBooked())
                        .setTrainCarriage(seatEntity.getTrainCarriage().getId()))
                .build();
    }

    @Override
    public BaseResponse<SeatResponse> deleteById(UUID id) {
        return null;
    }

    public List<SeatResponse> findSeatsByCarriageId(UUID id) {
        List<SeatEntity> content = dao.findSeatEntityByTrainCarriageId(id);
        List<SeatResponse> seats = new ArrayList<>();

        for (SeatEntity seatEntity : content) {
            seats.add(new SeatResponse()
                    .setId(seatEntity.getId())
                    .setSeatNumber(seatEntity.getSeatNumber())
                    .setUpdated(seatEntity.getUpdated())
                    .setCreated(seatEntity.getCreated())
                    .setTrainCarriage(seatEntity.getTrainCarriage().getId())
                    .setIsBooked(seatEntity.getIsBooked()));
        }
        return seats;
    }

    public void updateIsBooked(UUID seats) {
        dao.updateSeatIsBooked(seats);
    }

    public UUID getCarriageBySeatId(UUID id) {
        SeatResponse data = getById(id).getData();
        return data.getTrainCarriage();
    }
}
