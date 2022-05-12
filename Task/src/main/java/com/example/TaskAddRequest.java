package com.example;

public record TaskAddRequest( Integer Id,
                              String description,
                              Integer userId,
                              Integer groupId,
                              Boolean isActive
) {

}
