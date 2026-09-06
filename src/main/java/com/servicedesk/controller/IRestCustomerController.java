package com.servicedesk.controller;

import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustommerResponse;

public interface IRestCustomerController {
    
    public CustommerResponse<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
