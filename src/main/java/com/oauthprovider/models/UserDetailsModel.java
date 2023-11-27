package com.oauthprovider.models;

import lombok.Data;

@Data
public class UserDetailsModel extends UserBaseModel{
    
    private GroupDetailsModel group;

}
