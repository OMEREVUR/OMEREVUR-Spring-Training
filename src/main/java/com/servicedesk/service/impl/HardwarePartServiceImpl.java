package com.servicedesk.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicedesk.dto.DtoHardwarePart;
import com.servicedesk.model.HardwarePart;
import com.servicedesk.repository.HardwarePartRepository;
import com.servicedesk.service.IHardwarePartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HardwarePartServiceImpl implements IHardwarePartService{

    private final HardwarePartRepository hardwarePartRepository;

    @Override
    public DtoHardwarePart saveHardwarePart(DtoHardwarePart dto) {

    HardwarePart hardwarePart = new HardwarePart();
    hardwarePart.setPartName(dto.getPartName());
    hardwarePart.setPartCode(dto.getPartCode());
    hardwarePart.setPrice(dto.getPrice());
    hardwarePart.setStockQuantity(dto.getStockQuantity());

    HardwarePart saved = hardwarePartRepository.save(hardwarePart);


    return toDto(saved);
}


    @Override
    @Transactional(readOnly = true)
    public List<DtoHardwarePart> getAllHardwareParts() {
        return hardwarePartRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    
    private DtoHardwarePart toDto(HardwarePart hardwarePart) {
        DtoHardwarePart dto = new DtoHardwarePart();
        dto.setId(hardwarePart.getId());
        dto.setPartName(hardwarePart.getPartName());
        dto.setPartCode(hardwarePart.getPartCode());
        dto.setPrice(hardwarePart.getPrice());
        dto.setStockQuantity(hardwarePart.getStockQuantity());
        return dto;
    }

}
