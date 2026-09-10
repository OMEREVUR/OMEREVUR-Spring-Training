package com.servicedesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicedesk.model.RepairTicket;
import com.servicedesk.model.TicketStatus;

public interface RepairTicketRepository extends JpaRepository<RepairTicket, Long>{

    long countByCustomerIdAndStatusNot(Long customerId, TicketStatus status);

}
