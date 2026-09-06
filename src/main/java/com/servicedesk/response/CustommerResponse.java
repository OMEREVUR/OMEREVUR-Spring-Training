package com.servicedesk.response;

import lombok.Data;

@Data
public class CustommerResponse<E> {

    public boolean statusType;
    public int statusCode;
    public String message;
	private E variable;

	public CustommerResponse(boolean statusType, int statusCode, String message, E variable) {
		super();
		this.statusType = statusType;
		this.statusCode = statusCode;
		this.message = message;
		this.variable = variable;
	}

	public static <T> CustommerResponse<T> success(T variable){

		return new CustommerResponse<>(Response.SUCCESS.statusType, Response.SUCCESS.statusCode, Response.SUCCESS.message, variable);
	}

	public static <T> CustommerResponse<T> fail(T variable) {
		return new CustommerResponse<>(Response.FAIL.statusType, Response.FAIL.statusCode, Response.FAIL.message, variable);
	}

	public static <T> CustommerResponse<T> error(T variable) {
		return new CustommerResponse<>(Response.ERROR.statusType, Response.ERROR.statusCode, Response.ERROR.message, variable);
	}

}
