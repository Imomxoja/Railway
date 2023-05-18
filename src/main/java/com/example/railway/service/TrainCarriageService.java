package com.example.railway.service;

import com.example.railway.dao.RailwayDao;
import com.example.railway.dao.TrainCarriageDao;
import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.CarriageRequest;
import com.example.railway.domain.dto.request.SeatRequest;
import com.example.railway.domain.dto.response.CarriageResponse;
import com.example.railway.domain.dto.response.SeatResponse;
import com.example.railway.domain.entity.train.TrainCarriageEntity;
import com.example.railway.domain.enums.CarriageType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainCarriageService implements BaseService<CarriageRequest, BaseResponse<CarriageResponse>> {
    private final ModelMapper mapper;
    private final RailwayDao railwayDao;
    private final SeatService seatService;
    private final TrainCarriageDao dao;

    @Override
    public BaseResponse<CarriageResponse> create(CarriageRequest carriageRequest) {
        switch (carriageRequest.getCarriageType()) {
            case "VIP" -> {
                for (int i = 1; i < 3; i++) {
                    TrainCarriageEntity map1 = mapper.map(new CarriageRequest()
                            .setCarriageNumber(i)
                            .setCarriageType(CarriageType.VIP.name()), TrainCarriageEntity.class);

                    map1.setRailways(railwayDao.findById(carriageRequest.getRailways()).get());

                    TrainCarriageEntity save = dao.save(map1);
                    for (int j = 1; j < 11; j++) {
                        seatService.create(new SeatRequest(j, save.getId(), false));
                    }
                }
            }
            case "PLASKARD" -> {
                for (int i = 1; i < 4; i++) {
                    TrainCarriageEntity map1 = mapper.map(new CarriageRequest()
                            .setCarriageNumber(i)
                            .setCarriageType(CarriageType.PLASKARD.name()), TrainCarriageEntity.class);

                    map1.setRailways(railwayDao.findById(carriageRequest.getRailways()).get());

                    TrainCarriageEntity save = dao.save(map1);
                    for (int j = 1; j < 16; j++) {
                        seatService.create(new SeatRequest(j, save.getId(), false));
                    }
                }
            }
            case "KUPE" -> {
                for (int i = 1; i < 4; i++) {
                    TrainCarriageEntity map1 = mapper.map(new CarriageRequest()
                            .setCarriageNumber(i)
                            .setCarriageType(CarriageType.KUPE.name()), TrainCarriageEntity.class);

                    map1.setRailways(railwayDao.findById(carriageRequest.getRailways()).get());

                    TrainCarriageEntity save = dao.save(map1);
                    for (int j = 1; j < 11; j++) {
                        seatService.create(new SeatRequest(j, save.getId(), false));
                    }
                }
            }
        }

        return BaseResponse.<CarriageResponse>builder()
                .status(200)
                .message("Carriage successfully added")
                .build();
    }

    @Override
    public BaseResponse<CarriageResponse> getById(UUID id) {
        TrainCarriageEntity trainCarriageEntity = dao.findById(id).get();
        return BaseResponse.<CarriageResponse>builder()
                .data(new CarriageResponse()
                        .setRailways(trainCarriageEntity.getRailways().getId())
                        .setId(trainCarriageEntity.getId())
                        .setUpdated(trainCarriageEntity.getUpdated())
                        .setCreated(trainCarriageEntity.getCreated())
                        .setCarriageType(trainCarriageEntity.getCarriageType().name())
                        .setCarriageNumber(trainCarriageEntity.getCarriageNumber())).build();
    }

    @Override
    public BaseResponse<CarriageResponse> deleteById(UUID id) {
        return null;
    }

    public List<SeatResponse> findCarriagesByRailwayId(UUID id) {
        List<TrainCarriageEntity> carriages = dao.findCarriagesByRailwayId(id);
        List<SeatResponse> allSeats = new ArrayList<>();
        for (TrainCarriageEntity carriage : carriages) {
            List<SeatResponse> seats = new ArrayList<>(
                    seatService.findSeatsByCarriageId(carriage.getId()));
            allSeats.addAll(seats);
        }

        return allSeats;
    }

    public UUID getRailwayByCarriageId(UUID id) {
        UUID carriageId = seatService.getCarriageBySeatId(id);
        CarriageResponse data = getById(carriageId).getData();
        return data.getRailways();
    }
}
