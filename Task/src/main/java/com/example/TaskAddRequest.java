package com.example;

public record TaskAddRequest( String desc,
                              Integer userId,
                              Integer groupId,
                              boolean isActive
) {

}
