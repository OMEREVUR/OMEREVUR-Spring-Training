package com.servicedesk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerContact {

    private Long id;
    private String phone;
    private String address;
    private String emergencyPhone;
}
