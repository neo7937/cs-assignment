package com.cs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerLogDto {

    private String id;
    private String state;
    private String type;
    private String host;
    private long timestamp;
    private boolean alert;
    private long duration;
}
