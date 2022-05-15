package com.example;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public record UserController(UserService userService) {

    @PostMapping
    public Boolean addUser(@RequestBody UserRequest userRequest){
        if(userService.addUser(userRequest)==true)
            return true;

        return false;
    }

    @PostMapping("/getuser")
    public Users getUser(@RequestBody Map<String,String> req){

       return userService.getUser(req.get("username"), req.get("password"));
    }
}
