package com.servicedesk.controller;

import java.util.List;

import com.servicedesk.dto.DtoHardwarePart;
import com.servicedesk.response.CustomerResponse;

public interface IRestHardwarePartController {

	CustomerResponse<DtoHardwarePart> saveHardwarePart(DtoHardwarePart dto);

	// Listeleme endpoint'inin sözleşmesi. Service ile aynı dönüş tipini kullanır.
	CustomerResponse<List<DtoHardwarePart>> getAllHardwareParts();

}
