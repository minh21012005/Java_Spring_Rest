package vn.hoidanit.jobhunter.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.hoidanit.jobhunter.domain.RestRespone;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestRespone<Object>> handleIdInvalidException(IdInvalidException idInvalidException) {
        RestRespone<Object> restRespone = new RestRespone<Object>();
        restRespone.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restRespone.setError(idInvalidException.getMessage());
        restRespone.setMessage("Id invalid exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restRespone);
    }
}
