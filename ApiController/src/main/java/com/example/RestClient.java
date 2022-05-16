package com.example;

import antlr.Token;
import com.example.auth.TokenManager;
import com.example.request.GroupRequest;
import com.example.request.TaskRequest;
import com.example.request.UserRequest;
import com.fasterxml.classmate.util.ConcurrentTypeCache;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
//import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class RestClient {

    public static final String ADD_USER_API="http://host.docker.internal:3020/api/v1/users"; //post w body
    public static final String GET_USER_API="http://host.docker.internal:3020/api/v1/users/getuser"; //post username, post password;
    public static final String ADD_GROUP_API="http://host.docker.internal:3010/api/v1/groups"; // post groupRequest;
    public static final String DELETE_GROUP_API="http://host.docker.internal:3010/api/v1/groups"; // Delete Id
    public static final String UPDATE_GROUP_API="http://host.docker.internal:3010/api/v1/groups";// PUT groupRequest;
    public static final String GET_GROUP_LIST_API="http://host.docker.internal:3010/api/v1/groups/grouplist"; //POST userId;
    public static final String ADD_TASK_API="http://host.docker.internal:3000/api/v1/tasks"; //taskAddReq POST
    public static final String DELETE_TASK_API="http://host.docker.internal:3000/api/v1/tasks"; // delete Id;
    public static final String DELETE_GROUP_FROMTASK_API="http://host.docker.internal:3000/api/v1/tasks/group"; //delete groupId
    public static final String UPDATE_TASK_API="http://host.docker.internal:3000/api/v1/tasks"; //PUT taskReq;
    public static final String GET_USER_TASK_API="http://host.docker.internal:3000/api/v1/tasks/tasklist"; // post userId and bool;



    public static ResponseEntity<String> addUser(UserRequest userRequest) {

       /* HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(ADD_USER_API);
            JSONObject user = new JSONObject();
            user.put("userName","asdf");
            user.put("password","123");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            StringEntity entity = new StringEntity(user.toString());
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity result = response.getEntity();
            String end = EntityUtils.toString(result);
            System.out.println(end);
            return (ResponseEntity)response;
            //return Boolean.parseBoolean(end);

        } catch (Exception ex) {
            //return false;
            return null;
        } finally {
            // @Deprecated httpClient.getConnectionManager().shutdown();
        }*/

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject user = new JSONObject();
        user.put("userName",userRequest.username());
        String password =new BCryptPasswordEncoder().encode(userRequest.password());
        user.put("password",password);
        HttpEntity<String> entity = new HttpEntity<>(user.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(ADD_USER_API,entity,String.class);
        return response;

    }
        public static ResponseEntity<String> getUser(UserRequest userRequest)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject user = new JSONObject();
            user.put("username",userRequest.username());
            String password =new BCryptPasswordEncoder().encode(userRequest.password());
            user.put("password",password);
            HttpEntity<String> entity = new HttpEntity<>(user.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(GET_USER_API,entity,String.class);
            return response;
        }

        public static ResponseEntity<String> addTask(TaskRequest taskRequest, String token)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject task = new JSONObject();
            task.put("description",taskRequest.description());
            task.put("groupId",taskRequest.groupId());
            task.put("isActive",true);
            task.put("dueDate",taskRequest.dueDate());
            task.put("priority",taskRequest.priority());
            TokenManager tokenManager= new TokenManager();
            String user= tokenManager.getUsernameToken(token);
            task.put("userId",getUserPlain(user));
            HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(ADD_TASK_API,entity,String.class);
            return response;
        }


        public static String getUserPlain(String username){

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject user = new JSONObject();
            user.put("username",username);
            //String password =new BCryptPasswordEncoder().encode(userRequest.password());
            user.put("password","");
            HttpEntity<String> entity = new HttpEntity<>(user.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(GET_USER_API,entity,String.class);
            JSONObject jsonObject= new JSONObject(response.getBody());

            return jsonObject.get("id").toString();
        }

        public static Boolean deleteTask(Integer Id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject task = new JSONObject();
        task.put("Id",Id);
        HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try{
            restTemplate.exchange(DELETE_TASK_API, HttpMethod.DELETE,entity,String.class);
            return true;

        }
        catch (Exception e) {

        }
        return false;

    }

        public static Boolean updateTask(TaskRequest taskRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject task = new JSONObject();
        task.put("Id",taskRequest.Id());
        task.put("groupId",taskRequest.groupId());
        task.put("dueDate",taskRequest.dueDate());
        task.put("priority",taskRequest.priority());
        task.put("isActive",taskRequest.isActive());
        task.put("description",taskRequest.description());

        HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try{
            restTemplate.exchange(UPDATE_TASK_API, HttpMethod.PUT,entity,String.class);
            return true;

        }
        catch (Exception e) {

        }
        return false;

    }

        public static ResponseEntity<String> userTaskList(TaskRequest taskRequest,String token)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            JSONObject task = new JSONObject();
            task.put("active",taskRequest.isActive());
            TokenManager tokenManager= new TokenManager();
            String user= tokenManager.getUsernameToken(token);
            task.put("userId",getUserPlain(user));
            HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            try{
                return restTemplate.exchange(GET_USER_TASK_API, HttpMethod.POST,entity,String.class);


            }
            catch (Exception e) {

            }
            return null;


        }

        public static ResponseEntity<String> addGroup(GroupRequest groupRequest, String token){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject task = new JSONObject();
            task.put("name",groupRequest.name());
            TokenManager tokenManager= new TokenManager();
            String user= tokenManager.getUsernameToken(token);
            task.put("userId",getUserPlain(user));
            HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(ADD_GROUP_API,entity,String.class);
            return response;

        }

    public static ResponseEntity<String> deleteGroup(GroupRequest groupRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject group = new JSONObject();
        JSONObject groupforTask = new JSONObject();
        group.put("Id",groupRequest.Id());
        groupforTask.put("groupId",groupRequest.Id());
        HttpEntity<String> entity = new HttpEntity<>(group.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entityforTask = new HttpEntity<>(groupforTask.toString(), headers);
        RestTemplate restTemplateforTask = new RestTemplate();

        try{
            restTemplateforTask.exchange(DELETE_GROUP_FROMTASK_API, HttpMethod.DELETE,entityforTask,String.class);

                return restTemplate.exchange(DELETE_GROUP_API, HttpMethod.DELETE,entity,String.class);



        }
        catch (Exception e) {

        }
        return null;

    }

    public static ResponseEntity<String> updateGroup(GroupRequest groupRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject group = new JSONObject();
        group.put("Id",groupRequest.Id());
        group.put("name",groupRequest.name());

        HttpEntity<String> entity = new HttpEntity<>(group.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try{
            return restTemplate.exchange(UPDATE_GROUP_API, HttpMethod.PUT,entity,String.class);


        }
        catch (Exception e) {

        }
        return null;

    }

    public static ResponseEntity<String> userGroupList(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject task = new JSONObject();
        TokenManager tokenManager= new TokenManager();
        String user= tokenManager.getUsernameToken(token);
        task.put("userId",getUserPlain(user));
        HttpEntity<String> entity = new HttpEntity<>(task.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try{
            return restTemplate.exchange(GET_GROUP_LIST_API, HttpMethod.POST,entity,String.class);


        }
        catch (Exception e) {

        }
        return null;
    }
}
