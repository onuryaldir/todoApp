package com.example.request;

import java.sql.Date;

public record TaskRequest(Integer Id,
                          String description,
                          Integer userId,
                          Integer groupId,
                          Boolean isActive,

                          Date dueDate,
                          Integer priority
                             ) {

}
