package com.servicedesk.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("Kayıt bulunamadı", HttpStatus.NOT_FOUND),
    MAX_ACTIVE_TICKETS_EXCEEDED("Müşterinin aktif fiş sınırı doldu", HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR("Gönderilen veriler geçersiz", HttpStatus.BAD_REQUEST),
    DUPLICATE_RECORD("Kayıt veritabanı kısıtlarına takıldı (aynı email veya parça kodu olabilir)", HttpStatus.CONFLICT),
    GENERAL_EXCEPTION("Beklenmeyen bir hata oluştu", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    MessageType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
