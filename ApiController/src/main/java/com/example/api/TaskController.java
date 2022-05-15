package com.example.api;

import com.example.RestClient;
import com.example.auth.TokenManager;
import com.example.request.TaskRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {


@PostMapping()
    public static ResponseEntity<String> addTaskReq(@RequestBody TaskRequest taskRequest, @RequestHeader HttpHeaders headers){
    String token=headers.get("Authorization").toString().substring(7);
    return RestClient.addTask(taskRequest, token);



}

@DeleteMapping()
    public static Boolean deleteTaskReq(@RequestBody TaskRequest taskRequest){

   return RestClient.deleteTask(taskRequest.Id());
}
@PutMapping()
    public static Boolean addTaskReq(@RequestBody TaskRequest taskRequest){

    return RestClient.updateTask(taskRequest);

}

@PostMapping("/tasklist")
    public static ResponseEntity<String> getTaskListReq(@RequestBody TaskRequest taskRequest,@RequestHeader HttpHeaders headers){
    String token=headers.get("Authorization").toString().substring(7);
    return RestClient.userTaskList(taskRequest,token);
}


}
