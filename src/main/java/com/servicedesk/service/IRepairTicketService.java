package com.servicedesk.service;

import com.servicedesk.dto.DtoRepairTicket;
import com.servicedesk.dto.DtoRepairTicketIU;

public interface IRepairTicketService {

	DtoRepairTicket saveRepairTicket(DtoRepairTicketIU dto);

}
