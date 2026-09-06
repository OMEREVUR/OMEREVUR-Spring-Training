package com.servicedesk.service;


import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustommerResponse;


public interface ICustomerService {
    public CustommerResponse<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

    
}
