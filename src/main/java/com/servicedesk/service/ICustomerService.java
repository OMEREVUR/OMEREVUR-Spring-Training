package com.servicedesk.service;


import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.response.CustomerResponse;


public interface ICustomerService {
    CustomerResponse<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
    
}
