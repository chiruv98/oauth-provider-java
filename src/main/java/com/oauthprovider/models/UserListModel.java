package com.oauthprovider.models;

import java.util.List;

import lombok.Data;

@Data
public class UserListModel {

    private GroupDetailsModel group;
    private List<UserBaseModel> users;
    
}
