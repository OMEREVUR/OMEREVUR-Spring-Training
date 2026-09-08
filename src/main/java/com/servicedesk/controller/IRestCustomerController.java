package com.servicedesk.controller;

import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustomerResponse;

public interface IRestCustomerController {
    
    CustomerResponse<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
