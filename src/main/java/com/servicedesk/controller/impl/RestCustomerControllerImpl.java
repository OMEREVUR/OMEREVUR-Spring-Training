package com.servicedesk.controller.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicedesk.controller.IRestCustomerController;
import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerContact;
import com.servicedesk.dto.DtoCustomerContactIU;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustomerResponse;
import com.servicedesk.service.ICustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-desk")
@RequiredArgsConstructor
public class RestCustomerControllerImpl implements IRestCustomerController {

    private final ICustomerService customerService;

    @Override
    @PostMapping("/save/customer")
    public CustomerResponse<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return CustomerResponse.success(customerService.saveCustomer(dtoCustomerIU));
    }

    @Override
    @PutMapping("/set/customer/{id}/contact")
    public CustomerResponse<DtoCustomerContact> setContact(@PathVariable Long id,
            @Valid @RequestBody DtoCustomerContactIU dto) {
        return CustomerResponse.success(customerService.setContact(id, dto));
    }

}
