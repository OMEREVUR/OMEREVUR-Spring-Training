package com.servicedesk.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoRepairTicket;
import com.servicedesk.dto.DtoRepairTicketIU;
import com.servicedesk.model.Customer;
import com.servicedesk.model.RepairTicket;
import com.servicedesk.repository.CustomerRepository;
import com.servicedesk.repository.RepairTicketRepository;
import com.servicedesk.service.IRepairTicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepairTicketServiceImpl implements IRepairTicketService {

    private final RepairTicketRepository repairTicketRepository;
    private final CustomerRepository customerRepository;

    @Override
    public DtoRepairTicket saveRepairTicket(DtoRepairTicketIU dto) {
        
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı! ID: " + dto.getCustomerId()));

        
        RepairTicket repairTicket = new RepairTicket();
        repairTicket.setIssueDescription(dto.getIssueDescription());
        repairTicket.setStatus("PENDING");
        repairTicket.setCustomer(customer);
        
        RepairTicket savedTicket = repairTicketRepository.save(repairTicket);

        
        DtoRepairTicket responseDto = new DtoRepairTicket();
        BeanUtils.copyProperties(savedTicket, responseDto);
        
        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(customer, dtoCustomer);
        responseDto.setCustomer(dtoCustomer);

        return responseDto;
    }
}
