package com.example.railway.service;

import com.example.railway.dao.TicketDao;
import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.TicketRequest;
import com.example.railway.domain.dto.response.SeatResponse;
import com.example.railway.domain.dto.response.TicketResponse;
import com.example.railway.domain.dto.response.UserResponse;
import com.example.railway.domain.entity.seat.SeatEntity;
import com.example.railway.domain.entity.ticket.TicketEntity;
import com.example.railway.domain.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService implements BaseService<TicketRequest, BaseResponse<TicketResponse>> {
    private final ModelMapper mapper;
    private final UserService service;
    private final SeatService seatService;
    private final TicketDao ticketDao;
    @Override
    public BaseResponse<TicketResponse> create(TicketRequest ticketRequest) {
        UserResponse user = service.getById(ticketRequest.getUsers()).getData();
        SeatResponse seat = seatService.getById(ticketRequest.getSeats()).getData();

        if (!seat.getIsBooked()) {
            if (ticketRequest.getPrice() < user.getBalance()) {
                service.updateBalance(ticketRequest.getUsers(), ticketRequest.getPrice()); // ← update user balance
                seatService.updateIsBooked(ticketRequest.getSeats()); // ← update seat isBooked field
                TicketEntity map = mapper.map(ticketRequest, TicketEntity.class);
                map.setUsers(mapper.map(user, UserEntity.class));
                map.setSeats(mapper.map(seat, SeatEntity.class));
                ticketDao.save(map);

                return BaseResponse.<TicketResponse>builder()
                        .status(200)
                        .message("Successfully was purchased")
                        .build();
            }
        }
        return BaseResponse.<TicketResponse>builder()
                .status(400)
                .message("User balance not enough")
                .build();
    }

    @Override
    public BaseResponse<TicketResponse> getById(UUID id) {
        return null;
    }

    @Override
    public BaseResponse<TicketResponse> deleteById(UUID id) {
        return null;
    }
}
