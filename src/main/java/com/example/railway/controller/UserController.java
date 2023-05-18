package com.example.railway.controller;

import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.TicketRequest;
import com.example.railway.domain.dto.response.RailwayResponse;
import com.example.railway.domain.dto.response.TicketResponse;
import com.example.railway.service.RailwayService;
import com.example.railway.service.TicketService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final RailwayService railwayService;
    private final TicketService ticketService;

    @PostMapping("/buyTicket")
    public String buyTicket (@RequestParam("fromCity") String fromCity,
                             @RequestParam("toCity") String toCity,
                             @RequestParam("departureDate") LocalDate departureDate,
                             Model model) {

        model.addAttribute("railways", railwayService.getRailwayByCityAndDate(fromCity, toCity, departureDate));
        return "Railways";
    }

    @GetMapping("/choose/{id}")
    public String chooseRailway(@PathVariable(name = "id") UUID id, Model model) {
        model.addAttribute("seats", railwayService.getRailwaySeats(id));
        return "BuyTicket";
    }

    @GetMapping("/reservation/{seatId}")
    public String reservationSeat(@PathVariable("seatId") UUID id, Model model, HttpSession session) {
        RailwayResponse data = railwayService.getBySeatId(id).getData();
        UUID userId = (UUID) session.getAttribute("id");

        BaseResponse<TicketResponse> ticketResponse = ticketService.create(new TicketRequest()
                .setUsers(userId)
                .setSeats(id)
                .setCityFrom(data.getFromCity())
                .setCityTo(data.getToCity())
                .setDepartureDate(data.getDepartureDate())
                .setPrice(data.getPrice()));

        model.addAttribute("message", ticketResponse.getMessage());
        model.addAttribute("seats", railwayService.getRailwaySeats(data.getId()));
        return "BuyTicket";
    }

}
