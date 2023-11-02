package com.oauthprovider.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.services.UserGroupService;

/* 
 * @author Chiranjeevi
*/

@RestController
@RequestMapping ("/api/oauth-provider")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @PostMapping (path  = "/group")
    public ResponseEntity<GroupDetailsModel> createGroup(@RequestBody GroupRequestModel request) {
        return userGroupService.createGroup(request);
    } 

    @GetMapping (path = "/hello")
    @ResponseBody
    public String helloWorld () {
        return "Hello World!";
    }
}
