package com.example;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TaskService(TaskRepository taskRepository) {

    public void addTaskService(TaskAddRequest req){

    Task task = Task.builder()
            .description(req.description())
            .groupId(req.groupId())
            .userId(req.userId())
            .isActive(req.isActive())
            .build();
    System.out.println("SA");
    taskRepository.save(task);
    }
    public List<Task> getList(){


        return taskRepository.findAll();
    }
}
