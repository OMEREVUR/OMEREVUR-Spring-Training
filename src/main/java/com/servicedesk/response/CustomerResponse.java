package com.servicedesk.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse<E> {

	private boolean statusType;
	private int statusCode;
	private String message;
	private E variable;

	public CustomerResponse(boolean statusType, int statusCode, String message, E variable) {
		super();
		this.statusType = statusType;
		this.statusCode = statusCode;
		this.message = message;
		this.variable = variable;
	}

	public static <T> CustomerResponse<T> success(T variable) {

		return new CustomerResponse<>(Response.SUCCESS.isStatusType(), Response.SUCCESS.getStatusCode(),
				Response.SUCCESS.getMessage(), variable);
	}

	public static <T> CustomerResponse<T> fail(T variable) {
		return new CustomerResponse<>(Response.FAIL.isStatusType(), Response.FAIL.getStatusCode(), Response.FAIL.getMessage(),
				variable);
	}

	public static <T> CustomerResponse<T> error(T variable) {
		return new CustomerResponse<>(Response.ERROR.isStatusType(), Response.ERROR.getStatusCode(), Response.ERROR.getMessage(),
				variable);
	}

}
