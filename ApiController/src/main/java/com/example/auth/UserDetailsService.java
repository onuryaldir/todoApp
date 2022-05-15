package com.example.auth;

import com.example.RestClient;
import com.example.request.UserRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

   // private Map<String, String> users = new HashMap<>();




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {


        JSONObject obj = new JSONObject(username);
        String user = obj.get("userName").toString();
        String password = obj.get("password").toString();


        return new User(user,password,new ArrayList<>());

    } catch (Exception e){
        throw new UsernameNotFoundException(username);
    }
    }
}
