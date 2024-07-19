package com.SpringCloudApp.exception;

import com.ibb.boot.data.exception.policy.BusinessExceptionPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LocalException implements BusinessExceptionPolicy {

    EMAIL_ALREADY_EXISTS("Email zaten var", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND_BY_EMAIL("Email ile kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_BY_ID("ID ile kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    EMAIL_IS_NOT_VERIFIED("Email doğrulanmamış", HttpStatus.BAD_REQUEST),
    USER_ALREADY_VERIFIED("Kullanıcı zaten doğrulandı", HttpStatus.BAD_REQUEST),
    EXCEL_SCHEMA_NOT_FOUND_BY_ID("Eklenmek istenen Şema kayıtlarla yok", HttpStatus.NOT_FOUND),

    INVALID_VERIFY_CODE("Geçersiz doğrulama kodu", HttpStatus.BAD_REQUEST),
    DENEME("DENEME", HttpStatus.I_AM_A_TEAPOT),
    VERIFY_CODE_EXPIRED("Doğrulama kodunun süresi dolmuş", HttpStatus.BAD_REQUEST);



    //getExcelSchemaById
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
