package com.servicedesk.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicedesk.config.AppServiceProperties;
import com.servicedesk.dto.DtoCustomer;
import com.servicedesk.dto.DtoHardwarePart;
import com.servicedesk.dto.DtoRepairTicket;
import com.servicedesk.dto.DtoRepairTicketIU;
import com.servicedesk.exception.BaseException;
import com.servicedesk.exception.ErrorMessage;
import com.servicedesk.exception.MessageType;
import com.servicedesk.model.Customer;
import com.servicedesk.model.HardwarePart;
import com.servicedesk.model.RepairTicket;
import com.servicedesk.model.TicketStatus;
import com.servicedesk.repository.CustomerRepository;
import com.servicedesk.repository.HardwarePartRepository;
import com.servicedesk.repository.RepairTicketRepository;
import com.servicedesk.service.IRepairTicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepairTicketServiceImpl implements IRepairTicketService {

    private final RepairTicketRepository repairTicketRepository;
    private final CustomerRepository customerRepository;
    private final HardwarePartRepository hardwarePartRepository;
    private final AppServiceProperties appServiceProperties;

    @Override
    @Transactional
    public DtoRepairTicket saveRepairTicket(DtoRepairTicketIU dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Müşteri ID: " + dto.getCustomerId())));

        checkActiveTicketLimit(customer.getId());

        List<HardwarePart> parts = findParts(dto.getPartIds());

        RepairTicket repairTicket = new RepairTicket();
        repairTicket.setIssueDescription(dto.getIssueDescription());
        repairTicket.setStatus(TicketStatus.PENDING);
        repairTicket.setCustomer(customer);
        repairTicket.setParts(parts);
        repairTicket.setTotalPrice(calculateTotalPrice(parts));

        RepairTicket savedTicket = repairTicketRepository.save(repairTicket);

        // BeanUtils listeleri kopyalamadığı için bilet alanları elle eşleniyor
        DtoRepairTicket responseDto = new DtoRepairTicket();
        responseDto.setId(savedTicket.getId());
        responseDto.setIssueDescription(savedTicket.getIssueDescription());
        responseDto.setStatus(savedTicket.getStatus());
        responseDto.setTotalPrice(savedTicket.getTotalPrice());
        responseDto.setCreatedAt(savedTicket.getCreatedAt());
        responseDto.setParts(savedTicket.getParts().stream().map(this::toDtoPart).toList());

        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(customer, dtoCustomer);
        responseDto.setCustomer(dtoCustomer);

        return responseDto;
    }

    // COMPLETED dışındaki her fiş aktif sayılır
    private void checkActiveTicketLimit(Long customerId) {
        Integer limit = appServiceProperties.getMaxActiveTicketsPerCustomer();
        if (limit == null) {
            return;
        }

        long activeTickets = repairTicketRepository.countByCustomerIdAndStatusNot(customerId, TicketStatus.COMPLETED);
        if (activeTickets >= limit) {
            throw new BaseException(new ErrorMessage(MessageType.MAX_ACTIVE_TICKETS_EXCEEDED,
                    "Müşteri ID: " + customerId + ", sınır: " + limit));
        }
    }

    private List<HardwarePart> findParts(List<Long> partIds) {
        if (partIds == null || partIds.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> requestedIds = new HashSet<>(partIds);
        List<HardwarePart> parts = hardwarePartRepository.findAllById(requestedIds);

        if (parts.size() != requestedIds.size()) {
            Set<Long> foundIds = parts.stream().map(HardwarePart::getId).collect(Collectors.toSet());
            requestedIds.removeAll(foundIds);
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Parça ID: " + requestedIds));
        }

        return parts;
    }

    // Parça yoksa tutar henüz belli değil; fiyat parçalar tahsis edilince hesaplanır
    private Double calculateTotalPrice(List<HardwarePart> parts) {
        if (parts.isEmpty()) {
            return null;
        }

        double partsTotal = parts.stream().mapToDouble(HardwarePart::getPrice).sum();
        return partsTotal + appServiceProperties.getBaseHandlingFee();
    }

    private DtoHardwarePart toDtoPart(HardwarePart part) {
        DtoHardwarePart dto = new DtoHardwarePart();
        dto.setId(part.getId());
        dto.setPartName(part.getPartName());
        dto.setPartCode(part.getPartCode());
        dto.setPrice(part.getPrice());
        dto.setStockQuantity(part.getStockQuantity());
        return dto;
    }
}
