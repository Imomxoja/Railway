package com.example.railway.service;

import com.example.railway.dao.RailwayDao;
import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.CarriageRequest;
import com.example.railway.domain.dto.request.RailwayRequest;
import com.example.railway.domain.dto.response.RailwayResponse;
import com.example.railway.domain.dto.response.SeatResponse;
import com.example.railway.domain.entity.railway.RailwayEntity;
import com.example.railway.domain.enums.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RailwayService implements BaseService<RailwayRequest, BaseResponse<RailwayResponse>> {
    private final ModelMapper mapper;
    private final RailwayDao railwayDao;
    private final TrainCarriageService carriageService;

    @Override
    public BaseResponse<RailwayResponse> create(RailwayRequest railwayRequest) {
        Optional<RailwayEntity> byRailwayName = railwayDao.findByRailwayName(railwayRequest.getRailwayName());

        if (byRailwayName.isPresent()) {
            return BaseResponse.<RailwayResponse>builder()
                    .status(401)
                    .message(railwayRequest.getRailwayName() + " railway already exists")
                    .build();
        }

        RailwayEntity save = railwayDao.save(mapper.map(railwayRequest, RailwayEntity.class));

        carriageService.create(new CarriageRequest(null, railwayRequest.getCarriageType(), save.getId()));


        return BaseResponse.<RailwayResponse>builder()
                .status(200)
                .message(save.getRailwayName() + " railway successfully added")
                .data(mapper.map(save, RailwayResponse.class))
                .build();
    }

    @Override
    public BaseResponse<RailwayResponse> getById(UUID id) {
        return BaseResponse.<RailwayResponse>builder()
                .data(mapper.map(railwayDao.findById(id).get(), RailwayResponse.class))
                .build();
    }

    @Override
    public BaseResponse<RailwayResponse> deleteById(UUID id) {
        railwayDao.deleteById(id);
        return BaseResponse.<RailwayResponse>builder().build();
    }

    public List<RailwayResponse> getRailwayByCityAndDate(String fromCity, String toCity, LocalDate departureDate) {
        int fromCityOrdinal = 0;
        int toCityOrdinal = 0;
        for (City value : City.values()) {
            if (value.name().equals(fromCity)) {
                fromCityOrdinal = value.ordinal();
                if (value.name().equals(toCity)) {
                    toCityOrdinal = value.ordinal();
                }
            }
        }



        List<RailwayEntity> all = railwayDao.findAll();
        List<RailwayResponse> findRailwayByCitiesAndDate = new ArrayList<>();

        if (!all.isEmpty()) {
            for (RailwayEntity railwayEntity : all) {
                if ((railwayEntity.getFromCity().ordinal() == fromCityOrdinal ||
                        railwayEntity.getFromCity().ordinal() < fromCityOrdinal) &&
                        (railwayEntity.getToCity().ordinal() == toCityOrdinal ||
                        railwayEntity.getToCity().ordinal() > toCityOrdinal) &&
                        (railwayEntity.getDepartureDate().equals(departureDate))) {
                    findRailwayByCitiesAndDate.add(mapper.map(railwayEntity, RailwayResponse.class));
                }
            }
        }
        return findRailwayByCitiesAndDate;
    }


    public List<SeatResponse> getRailwaySeats(UUID id) {
        return carriageService.findCarriagesByRailwayId(id);
    }

    public BaseResponse<RailwayResponse> getBySeatId(UUID id) {
        UUID railwayId = carriageService.getRailwayByCarriageId(id);
        return BaseResponse.<RailwayResponse>builder()
                .data(getById(railwayId).getData())
                .build();
    }
}
