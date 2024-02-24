package com.example.training_management_api.exception;

import com.example.training_management_api.exception.common.BusinessException;
import com.example.training_management_api.exception.common.ServiceError;

public class CommonException extends BusinessException {

    public CommonException(ServiceError serviceError) {
        super(serviceError, null, null);
    }
}