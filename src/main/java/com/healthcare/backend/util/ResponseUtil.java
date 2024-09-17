package com.healthcare.backend.util;

import com.healthcare.backend.domain.enums.StatusCode;
import com.healthcare.backend.domain.enums.StatusMessage;
import com.healthcare.backend.domain.response.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class ResponseUtil {

    private final HttpServletRequest servletRequest;

    public ResponseUtil(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ResponseEntity<APIResponse> wrapSuccess(Object value, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder()
                .statusCode(StatusCode.SUCCESS.valueOf())
//                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
                .origin(servletRequest.getRequestURI())
                .statusMessage(StatusMessage.SUCCESS.valueOf())
                .responseTime(DateTimeUtils.format(new Date()))
                .result(value)
                .build();
        return ResponseEntity.status(httpStatus).body(apiResponse);

    }

    public ResponseEntity<APIResponse> wrapError(Object value, String errorType, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder().statusCode(StatusCode.FAILURE.valueOf())
//                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
                .statusMessage(StatusMessage.FAILURE.valueOf())
                .errorType(errorType)
                .origin(servletRequest.getRequestURI())
                .responseTime(DateTimeUtils.format(new Date()))
                .result(Collections.singletonMap(Constant.ERROR, value))
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
