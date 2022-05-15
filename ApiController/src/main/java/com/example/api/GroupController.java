package com.example.api;

import com.example.RestClient;
import com.example.request.GroupRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @PostMapping
    public ResponseEntity<String> addGroup(@RequestBody GroupRequest groupRequest,@RequestHeader HttpHeaders headers){
        String token=headers.get("Authorization").toString().substring(7);
       return RestClient.addGroup(groupRequest,token);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteGroup(@RequestBody GroupRequest groupRequest) {

        return RestClient.deleteGroup(groupRequest);
    }

    @PutMapping
    public ResponseEntity<String> updateGroup(@RequestBody GroupRequest groupRequest){

        return RestClient.updateGroup(groupRequest);
    }
    @GetMapping("/grouplist")
    public ResponseEntity<String> getUserGroups(@RequestHeader HttpHeaders headers)
    {
        String token=headers.get("Authorization").toString().substring(7);
        return RestClient.userGroupList(token);

    }

}
