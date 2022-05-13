package com.example;

import java.sql.Date;

public record TaskAddRequest(Integer Id,
                             String description,
                             Integer userId,
                             Integer groupId,
                             Boolean isActive,

                             Date dueDate,
                             Integer priority
                             ) {

}
