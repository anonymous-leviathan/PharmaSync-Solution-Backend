package com.healthcare.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String ERROR_REASON = "Error Reason ";

    //some custom error messages
    public static final String JWT_ERROR = "JWT Error!";
    public static final String JWT_EXPIRED = "JWT Expired!";
    public static final String DATABASE_ACCESS_ERROR = "Database Access Error!";
    public static final String USER_NAME_NOT_FOUND = "Username Not Found!";


    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";



    public static final String EMPTY_STRING = "";
    public static final String APP_TRACE_ID = "app-trace-id";
    public static final String ERROR = "error";
}
