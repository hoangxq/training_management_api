package com.example.training_management_api.exception.common;

import lombok.Getter;

import java.io.Serial;
import java.util.LinkedHashMap;

/**
 * thing need logging only
 * @author Admin
 *
 */
@Getter
public class SysException extends ServiceException {

    private final String printMessage;
    
    /**
     * 3374372229890332024L
     */
    @Serial
    private static final long serialVersionUID = 3374372229890332024L;

    protected SysException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err, ex, params);
        printMessage = "";
    }

    public SysException(String printMessage, Throwable ex) {
        super(ServiceError.UNEXPECTED_EXCEPTION, ex, null);
        this.printMessage = printMessage;
    }

    public SysException(String printMessage) {
        this(printMessage, null);
    }

}