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
    public String addTask(@RequestBody TaskAddRequest taskAddRequest){
        System.out.println(taskAddRequest.toString());
        if(1<=taskAddRequest.priority() && taskAddRequest.priority()<=5) {
            taskService.addTaskService(taskAddRequest);
            return "done";
        }
         return "priority must be between 1-5";
    }

    @DeleteMapping
    public String deleteTask(@RequestBody Map<String,Integer> req){

        taskService.deleteTask(req.get("Id"));


        return "done";
    }
    @DeleteMapping("/group")
    public String deleteGroup(@RequestBody Map<String,Integer> req){

        taskService.deleteGroup(req.get("groupId"));

        return "done";
    }
    @PutMapping
    public String updateTask(@RequestBody TaskAddRequest taskAddRequest){

       taskService.updateTask(taskAddRequest);

       return "done";

    }

    @PostMapping("tasklist")
        public List<Task> getUserTasks(@RequestBody Map<String,String> req){

        System.out.println(req);
        return taskService.getUserActiveTaskList(Integer.valueOf(req.get("userId")), Boolean.parseBoolean(req.get("active")));
    }




}
