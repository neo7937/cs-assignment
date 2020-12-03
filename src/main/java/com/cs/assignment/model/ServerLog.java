package com.cs.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SERVER_LOG")
public class ServerLog {

    @Id
    private String id;
    private String type;
    private String host;
    private boolean alert;
    private long duration;
}
