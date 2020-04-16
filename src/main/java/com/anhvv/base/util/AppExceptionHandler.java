package com.anhvv.base.util;

import com.anhvv.base.common.BusinessException;
import com.anhvv.base.common.ResponseBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBase> handlerBusinessException(BusinessException ex) {
        logger.info("Error business exception");
        ResponseBase responseBase = ResponseBase.build()
                .setStatus(ex.getErrorCode())
                .setMessageCode(ex.getMessage());
        return ResponseEntity.ok(responseBase);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> handlerException(Exception ex) {
        logger.error("Error exception: ", ex.getMessage());
        ResponseBase responseBase = ResponseBase.build()
                .setStatus("500")
                .setMessageCode("System error. Please try again");
        return ResponseEntity.ok(responseBase);
    }
}
