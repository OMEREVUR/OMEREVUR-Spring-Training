package com.servicedesk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoHardwarePart {

    private Long id;
    private String partName;
    private String partCode;
    private Double price;
}
