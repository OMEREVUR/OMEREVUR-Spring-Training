package com.servicedesk.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoCustomerContact;
import com.servicedesk.dto.DtoCustomerContactIU;
import com.servicedesk.dto.DtoCustomerIU;
import com.servicedesk.exception.BaseException;
import com.servicedesk.exception.ErrorMessage;
import com.servicedesk.exception.MessageType;
import com.servicedesk.model.Customer;
import com.servicedesk.model.CustomerContact;
import com.servicedesk.repository.CustomerRepository;
import com.servicedesk.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        Customer customer = new Customer();
        customer.setFirstName(dtoCustomerIU.getFirstName());
        customer.setLastName(dtoCustomerIU.getLastName());
        customer.setEmail(dtoCustomerIU.getEmail());

        if (dtoCustomerIU.getContact() != null) {
            CustomerContact contact = new CustomerContact();
            contact.setPhone(dtoCustomerIU.getContact().getPhone());
            contact.setAddress(dtoCustomerIU.getContact().getAddress());
            contact.setEmergencyPhone(dtoCustomerIU.getContact().getEmergencyPhone());

            customer.setContact(contact);
        }

        Customer savedCustomer = customerRepository.save(customer);

        DtoCustomer dtoCustomer = new DtoCustomer();
        dtoCustomer.setId(savedCustomer.getId());
        dtoCustomer.setFirstName(savedCustomer.getFirstName());
        dtoCustomer.setLastName(savedCustomer.getLastName());
        dtoCustomer.setEmail(savedCustomer.getEmail());
        dtoCustomer.setCreatedAt(savedCustomer.getCreatedAt());

        if (savedCustomer.getContact() != null) {
            dtoCustomer.setContact(toDtoContact(savedCustomer.getContact()));
        }

        return dtoCustomer;
    }

    @Override
    @Transactional
    public DtoCustomerContact setContact(Long id, DtoCustomerContactIU dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Müşteri ID: " + id)));

        CustomerContact contact = customer.getContact() != null ? customer.getContact() : new CustomerContact();
        contact.setPhone(dto.getPhone());
        contact.setAddress(dto.getAddress());
        contact.setEmergencyPhone(dto.getEmergencyPhone());
        customer.setContact(contact);

        Customer savedCustomer = customerRepository.save(customer);

        return toDtoContact(savedCustomer.getContact());
    }

    private DtoCustomerContact toDtoContact(CustomerContact contact) {
        DtoCustomerContact dto = new DtoCustomerContact();
        dto.setId(contact.getId());
        dto.setPhone(contact.getPhone());
        dto.setAddress(contact.getAddress());
        dto.setEmergencyPhone(contact.getEmergencyPhone());
        return dto;
    }
}
