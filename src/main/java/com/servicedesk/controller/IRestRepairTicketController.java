package com.servicedesk.controller;

import com.servicedesk.dto.DtoRepairTicket;
import com.servicedesk.dto.DtoRepairTicketIU;
import com.servicedesk.response.CustomerResponse;

public interface IRestRepairTicketController {

    CustomerResponse<DtoRepairTicket> saveRepairTicket(DtoRepairTicketIU dto);
}
