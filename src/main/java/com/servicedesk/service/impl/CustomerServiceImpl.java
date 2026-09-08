package com.servicedesk.service.impl;

import org.springframework.stereotype.Service;

import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.model.Customer;
import com.servicedesk.repository.CustomerRepository;
import com.servicedesk.response.CustomerResponse;
import com.servicedesk.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse<DtoCustomer> saveCustomer(
            DtoCustomerIU dtoCustomerIU) {
        Customer customer = new Customer();
        customer.setFirstName(dtoCustomerIU.getFirstName());
        customer.setLastName(dtoCustomerIU.getLastName());
        customer.setEmail(dtoCustomerIU.getEmail());

        Customer savedCustomer = customerRepository.save(customer);
        DtoCustomer dtoCustomer = new DtoCustomer();

        dtoCustomer.setId(savedCustomer.getId());
        dtoCustomer.setFirstName(savedCustomer.getFirstName());
        dtoCustomer.setLastName(savedCustomer.getLastName());
        dtoCustomer.setEmail(savedCustomer.getEmail());
        dtoCustomer.setCreatedAt(savedCustomer.getCreatedAt());

        return CustomerResponse.success(dtoCustomer);

    }
}
