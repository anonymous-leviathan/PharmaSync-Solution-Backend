package com.healthcare.backend.util;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
//    private final HttpServletRequest servletRequest;
//
//    public ResponseUtils(HttpServletRequest servletRequest) {
//        this.servletRequest = servletRequest;
//    }
//
//    /**
//     * This will construct the success respond of API.
//     *
//     * @param value
//     * @param httpStatus
//     * @return
//     */
//    public ResponseEntity<APIResponse> wrapSuccess(Object value, HttpStatus httpStatus) {
//        APIResponse apiResponse = APIResponse.builder()
//                .statusCode(StatusCode.SUCCESS.valueOf())
//                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
//                .origin(servletRequest.getRequestURI())
//                .statusMessage(StatusMessage.SUCCESS.valueOf())
//                .responseTime(DateTimeUtils.format(new Date()))
//                .result(value)
//                .build();
//        return ResponseEntity.status(httpStatus).body(apiResponse);
//
//    }
//
//    /**
//     * This will construct the success respond of API.
//     *
//     * @param value
//     * @param httpStatus
//     * @return
//     */
//    public ResponseEntity<APIResponse> wrapError(Object value, String errorType, HttpStatus httpStatus) {
//        APIResponse apiResponse = APIResponse.builder().statusCode(StatusCode.FAILURE.valueOf())
//                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
//                .statusMessage(StatusMessage.FAILURE.valueOf())
//                .errorType(errorType)
//                .origin(servletRequest.getRequestURI())
//                .responseTime(DateTimeUtils.format(new Date()))
//                .result(Collections.singletonMap(AppConstants.ERROR, value))
//                .build();
//
//        return ResponseEntity.status(httpStatus).body(apiResponse);
//    }
}
