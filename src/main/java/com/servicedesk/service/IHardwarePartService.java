package com.servicedesk.service;

import java.util.List;

import com.servicedesk.dto.DtoHardwarePart;

public interface IHardwarePartService {

	DtoHardwarePart saveHardwarePart(DtoHardwarePart dto);

	List<DtoHardwarePart> getAllHardwareParts();

}
