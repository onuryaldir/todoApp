package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/tasks")
public record TaskController(TaskService taskService) {


    @PostMapping
    public void addTask(@RequestBody TaskAddRequest taskAddRequest){
        System.out.println(taskAddRequest.toString());
        taskService.addTaskService(taskAddRequest);
    }
    @GetMapping
    public List<Task> listTasks(){

        System.out.println("Get Yaptim");
        return taskService.getList();
    }
    @DeleteMapping
    public void deleteTask(){}

    @PutMapping
    public void updateTask(){}
}
