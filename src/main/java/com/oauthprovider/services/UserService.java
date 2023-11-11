package com.oauthprovider.services;

import com.oauthprovider.models.UserRequestModel;

public interface UserService {

    Object createUser (String groupId, UserRequestModel request);
    Object listUsers (String groupId);
    
}
