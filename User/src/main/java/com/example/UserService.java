package com.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository) {



    public Boolean addUser(UserRequest req){

        Users user = Users.builder()
                .userName(req.userName())
                .password(req.password())
                .build();
       List<Users> u =  userRepository.findByUserName(req.userName());

        if(u.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public Users getUser(String userName , String password){

        if(!userRepository.findByUserName(userName).isEmpty())
         return userRepository.findByUserName(userName).get(0);
        return null;
    }
}
