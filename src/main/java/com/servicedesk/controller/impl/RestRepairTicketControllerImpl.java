package com.servicedesk.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicedesk.controller.IRestRepairTicketController;
import com.servicedesk.dto.DtoRepairTicket;
import com.servicedesk.dto.DtoRepairTicketIU;
import com.servicedesk.response.CustomerResponse;
import com.servicedesk.service.IRepairTicketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-desk")
@RequiredArgsConstructor
public class RestRepairTicketControllerImpl implements IRestRepairTicketController {

    private final IRepairTicketService repairTicketService;

    @Override
    @PostMapping("/save/ticket")
    public CustomerResponse<DtoRepairTicket> saveRepairTicket(@Valid @RequestBody DtoRepairTicketIU dto) {
        return CustomerResponse.success(repairTicketService.saveRepairTicket(dto));
    }
}
