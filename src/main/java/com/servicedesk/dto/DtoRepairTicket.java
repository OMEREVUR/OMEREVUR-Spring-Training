package com.servicedesk.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoRepairTicket {

    private Long id;
    private String issueDescription;
    private String status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private DtoCustomer customer;
    private List<DtoHardwarePart> parts;
}
