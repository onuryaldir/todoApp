package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService = new TaskService();
    @PostMapping
    public void addTask(@RequestBody TaskAddRequest taskAddRequest){
        System.out.println(taskAddRequest.toString());
        taskService.addTaskService(taskAddRequest);
    }
    @GetMapping
    public void listTasks(){

        System.out.println("Get Yaptim");

    }
    @DeleteMapping
    public void deleteTask(){}

    @PutMapping
    public void updateTask(){}
}
