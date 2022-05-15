package com.example.api;

import com.example.RestClient;
import com.example.auth.TokenManager;
import com.example.request.LoginRequest;
import com.example.request.UserRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) {
        try {
            String res =RestClient.getUser(userRequest).getBody();
            if(res!=null) {
                JSONObject obj = new JSONObject(res);
                String username = obj.get("userName").toString();
                String password = obj.get("password").toString();
                if(new BCryptPasswordEncoder().matches(userRequest.password(),password))
                    return ResponseEntity.ok(tokenManager.generateToken(userRequest.username()));

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong Password");
            }
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequest userRequest){

        return RestClient.addUser(userRequest);

    }
}
