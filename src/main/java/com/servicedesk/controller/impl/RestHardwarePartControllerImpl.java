package com.servicedesk.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicedesk.controller.IRestHardwarePartController;
import com.servicedesk.dto.DtoHardwarePart;
import com.servicedesk.response.CustomerResponse;
import com.servicedesk.service.IHardwarePartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-desk")
@RequiredArgsConstructor
public class RestHardwarePartControllerImpl implements IRestHardwarePartController {

	private final IHardwarePartService hardwarePartService;

	@Override
	@PostMapping("save/hardware-part")
	public CustomerResponse<DtoHardwarePart> saveHardwarePart(@Valid @RequestBody DtoHardwarePart dto) {

		return CustomerResponse.success(hardwarePartService.saveHardwarePart(dto));
	}

	@Override
	@GetMapping("/list/hardware-part")
	public CustomerResponse<List<DtoHardwarePart>> getAllHardwareParts() {
		return CustomerResponse.success(hardwarePartService.getAllHardwareParts());
	}

}
