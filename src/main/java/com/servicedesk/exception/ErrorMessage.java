package com.servicedesk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage() {
        if (ofStatic == null) {
            return messageType.getMessage();
        }
        return messageType.getMessage() + " : " + ofStatic;
    }
}
