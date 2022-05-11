package com.example;

public record TaskAddRequest( String description,
                              Integer userId,
                              Integer groupId,
                              boolean isActive
) {

}
