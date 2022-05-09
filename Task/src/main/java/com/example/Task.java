package com.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

    private Integer Id;
    private String desc;
    private boolean isActive;
    private Integer userId;
    private Integer groupId;

}
