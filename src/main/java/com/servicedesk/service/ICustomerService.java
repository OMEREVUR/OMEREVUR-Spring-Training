package com.servicedesk.service;


import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerContact;
import com.servicedesk.dto.DtoCustomerContactIU;
import com.servicedesk.dto.DtoCustomerIU;


public interface ICustomerService {

    DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    DtoCustomerContact setContact(Long id, DtoCustomerContactIU dto);

}
