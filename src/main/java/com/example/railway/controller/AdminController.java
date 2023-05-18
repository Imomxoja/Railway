package com.example.railway.controller;

import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.RailwayRequest;
import com.example.railway.domain.dto.response.RailwayResponse;
import com.example.railway.service.RailwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final RailwayService railwayService;

    @PostMapping("/create")
    public String createRailway(@ModelAttribute RailwayRequest railwayRequest, Model model) {
        BaseResponse<RailwayResponse> railwayResponse = railwayService.create(railwayRequest);

        model.addAttribute("message", railwayResponse.getMessage());
        return "AdminWindow";
    }


}
