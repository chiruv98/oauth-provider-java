package com.oauthprovider.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.exception.ErrorResponse;
import com.oauthprovider.exception.GlobalExceptionHandler;
import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.services.UserGroupService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/* 
 * @author Chiranjeevi
*/

@RestController
@Slf4j

@RequestMapping ("/api/oauth-provider")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    @PostMapping (path = "/group")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupRequestModel request) throws MethodArgumentNotValidException {

        if (request.getGroupName().length() < 3) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("GroupName cannot be less than 3 characters");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                400, 
                "1234", 
                message, 
                "BAD_REQUEST",
                currentUri);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Object response = userGroupService.createGroup(request);

        if (response.getClass() == ErrorResponse.class) {
            return new ResponseEntity<ErrorResponse>((ErrorResponse) response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<GroupDetailsModel>((GroupDetailsModel) response, HttpStatus.OK);
    }

    @GetMapping (path = "/group/{groupId}")
    public ResponseEntity<?> getGroup(@PathVariable("groupId") @NotNull String id) {
        log.info("id: {}", id);

        GroupDetailsModel response = (GroupDetailsModel) userGroupService.getGroup(id);

        if (response == null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("No data found for given group id");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                404, 
                "1234", 
                message, 
                "NO_DATA_FOUND",
                currentUri);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GroupDetailsModel>(response, HttpStatus.OK); 
    }

    @PatchMapping (path = "/group/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable("groupId") @NotNull String id, @Valid @RequestBody GroupRequestModel request) {
        log.info("id: {}", id);

        Object response = userGroupService.updateGroup(id, request);

        if (response == null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("No data found for given user id");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                404, 
                "1234", 
                message, 
                "NO_DATA_FOUND",
                currentUri);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }

        if (response.getClass() == ErrorResponse.class) {
            return new ResponseEntity<ErrorResponse>((ErrorResponse) response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<GroupDetailsModel>((GroupDetailsModel) response, HttpStatus.OK);
    }

    @GetMapping (path = "/hello")
    @ResponseBody
    public String helloWorld () {
        return "Hello World!";
    }
}
