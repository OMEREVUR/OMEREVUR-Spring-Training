package com.servicedesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerContactIU {

    @NotBlank(message = "Telefon boş olamaz")
    @Size(max = 20, message = "Telefon en fazla 20 karakter olabilir")
    private String phone;

    @NotBlank(message = "Adres boş olamaz")
    private String address;

    @Size(max = 20, message = "Acil telefon en fazla 20 karakter olabilir")
    private String emergencyPhone;
}
