package com.oauthprovider.services;

import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.models.UserRequestModel;

/* 
 * @author Chiranjeevi
*/

public interface UserGroupService {

    Object createGroup (GroupRequestModel request);
    Object getGroup (String id);
    Object updateGroup (String id, GroupRequestModel request);

    Object createUser (String groupId, UserRequestModel request);
    
}
