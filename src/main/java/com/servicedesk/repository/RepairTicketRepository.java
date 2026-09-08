package com.servicedesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicedesk.model.RepairTicket;

public interface RepairTicketRepository extends JpaRepository<RepairTicket, Long>{

}
