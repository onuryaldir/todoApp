package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/tasks")
public record TaskController(TaskService taskService) {


    @PostMapping
    public void addTask(@RequestBody TaskAddRequest taskAddRequest){
        System.out.println(taskAddRequest.toString());
        taskService.addTaskService(taskAddRequest);
    }

    @DeleteMapping
    public void deleteTask(@RequestBody Map<String,Integer> req){

        taskService.deleteTask(req.get("Id"));
    }

    @PutMapping
    public void updateTask(@RequestBody TaskAddRequest taskAddRequest){

       taskService.updateTask(taskAddRequest);



    }

    @PostMapping("tasklist")
        public List<Task> getUserTasks(@RequestBody Map<String,String> req){

        System.out.println(req);
        return taskService.getUserActiveTaskList(Integer.valueOf(req.get("userId")), Boolean.parseBoolean(req.get("active")));
    }




}
