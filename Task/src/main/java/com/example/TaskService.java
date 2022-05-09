package com.example;

public record TaskService() {


    public void addTaskService(TaskAddRequest req){

    Task task = Task.builder()
            .desc(req.desc())
            .groupId(req.groupId())
            .userId(req.userId())
            .isActive(req.isActive())
            .build();
    System.out.println("SA");
    }
}
