package com.servicedesk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "firstName", "lastName", "email", "createTime"})
public class DtoCustomer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createTime;

}
	