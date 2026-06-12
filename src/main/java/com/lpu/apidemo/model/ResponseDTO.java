package com.lpu.apidemo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ResponseDTO {
    private int statusCode;
    private String message;
    private List<UserDTO> dtos;
    private boolean error;
}
