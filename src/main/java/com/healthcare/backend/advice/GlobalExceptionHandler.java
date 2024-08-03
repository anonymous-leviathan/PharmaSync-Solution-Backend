package com.healthcare.backend.advice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.healthcare.backend.util.Constant.*;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({JwtException.class})
    public ProblemDetail handleJwtException(JwtException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setProperty(ERROR_REASON, JWT_ERROR);
        return problemDetail;
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ProblemDetail handleExpiredJwtException(ExpiredJwtException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setProperty(ERROR_REASON, JWT_EXPIRED);
        return problemDetail;
    }

    @ExceptionHandler({DataAccessException.class})
    public ProblemDetail handleDataAccessException(DataAccessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty(ERROR_REASON, DATABASE_ACCESS_ERROR);
        return problemDetail;
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty(ERROR_REASON, USER_NAME_NOT_FOUND);
        return problemDetail;
    }
}