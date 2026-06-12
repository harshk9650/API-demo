package com.lpu.apidemo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private int status;
    private String msg;
    private LocalDateTime dateTime;
    private String path;
}
