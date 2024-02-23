package com.example.training_management_api.dto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {

    protected String message;
    protected Object data;

    public static Response of(String message, Object data) {
        return Response.builder()
                .message(message)
                .data(data)
                .build();
    }

}
