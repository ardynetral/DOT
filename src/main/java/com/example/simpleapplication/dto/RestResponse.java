package com.example.simpleapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResponse {
    private String httpCode;
    private Object response;
}
