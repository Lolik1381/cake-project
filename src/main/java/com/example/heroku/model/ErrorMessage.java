package com.example.heroku.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private int status;
    private String error;
    private String message;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime timestamp;
}