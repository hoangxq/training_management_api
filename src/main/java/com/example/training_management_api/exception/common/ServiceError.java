package com.example.training_management_api.exception.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ServiceError {
    INVALID_TOKEN(401, "err.authorize.invalid-token"),
    INVALID_TOKEN_FORMAT(401, "err.authorize.invalid-token-format"),
    TOKEN_INFO_INCORRECT(401, "err.authorize.token-info-incorrect"),
    SESSION_EXPIRED(401, "err.authorize.session-expired"),
    CMN_ACCESS_DENIED(401, "err.authorize.access-denied"),

    CMN_INVALID_PARAM(400, "err.api.invalid-param"),
    CMN_MESSAGE_NOT_READABLE(400, "err.api.message-not-readable"),

    ENTITY_NOT_FOUND(404, "err.api.entity-not-found"),
    CMN_HTTP_METHOD_NOT_SUPPORT(405, "err.api.http-method-not-support"),
    CMN_MEDIA_TYPE_NOT_SUPPORT(415, "err.api.media-type-not-support"),
    ENDPOINT_NOT_FOUND(404, "err.api.endpoint-not-found"),

    CURRENT_USER_NOT_FOUND(500,"err.sys.current-user-not-found"),

    UNEXPECTED_EXCEPTION(500, "err.sys.unexpected-exception");

    ServiceError(int errCode, String messageKey) {
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    private static final Map<String, ServiceError> ERROR_MAP = new HashMap<>();

    static {
        for (ServiceError e : values()) {
            ERROR_MAP.put(e.getMessageKey(), e);
        }
    }

    private final int errCode;
    private final String messageKey;

    public static ServiceError getByMessageKey(String messageKey) {
        return Optional.ofNullable(ERROR_MAP.get(messageKey))
                .orElse(UNEXPECTED_EXCEPTION);
    }

}
