package com.servicedesk.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.servicedesk.controller.IRestCustomerController;
import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustomerResponse;
import com.servicedesk.service.ICustomerService;

@RestController
@RequestMapping("/api/service-desk")
@RequiredArgsConstructor
public class RestCustomerControllerImpl implements IRestCustomerController {

    private final ICustomerService customerService;

    @Override
    @PostMapping("/save/customer")
    public CustomerResponse<DtoCustomer> saveCustomer(@RequestBody DtoCustomerIU dtoCustomerIU) {
        return customerService.saveCustomer(dtoCustomerIU);
    }


}
