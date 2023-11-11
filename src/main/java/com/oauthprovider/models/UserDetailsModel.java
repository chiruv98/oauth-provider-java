package com.oauthprovider.models;

import lombok.Data;

@Data
public class UserDetailsModel {
    
    private String id;
    private String firstname;
    private String lastName;
    private String email;
    private GroupDetailsModel group;

}
