package com.servicedesk.response;

import lombok.Getter;

@Getter 
public enum Response {

    SUCCESS(true, 200, "Success"), FAIL(false, 400, "Fail"), ERROR(false, 500, "Error");

    private boolean statusType;
    private int statusCode;
    private String message;

    Response(boolean statusType, int statusCode, String message) {
        this.statusType = statusType;
        this.statusCode = statusCode;
        this.message = message;
    }

}
