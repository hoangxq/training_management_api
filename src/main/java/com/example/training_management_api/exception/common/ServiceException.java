package com.example.training_management_api.exception.common;

import lombok.Getter;

import java.io.Serial;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public abstract class ServiceException extends RuntimeException {

    private final ServiceError err;
    private final Map<String, Object> params;
    
    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = -6662460118306199774L;
    
    protected ServiceException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err.getMessageKey(), ex);
        this.params = Objects.nonNull(params) ? params: Collections.emptyMap();
        this.err = err;
    }

}