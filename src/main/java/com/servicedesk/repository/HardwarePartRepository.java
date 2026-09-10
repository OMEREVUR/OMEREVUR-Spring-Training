package com.servicedesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicedesk.model.HardwarePart;

public interface HardwarePartRepository extends JpaRepository<HardwarePart, Long>{

}
