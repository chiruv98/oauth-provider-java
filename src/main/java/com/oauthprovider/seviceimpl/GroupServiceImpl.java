package com.oauthprovider.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.oauthprovider.services.UserGroupService;
import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.entities.GroupEntity;
import com.oauthprovider.repositories.GroupRepository;

/* 
 * @author Chiranjeevi
*/

@Service
public class GroupServiceImpl implements UserGroupService {
 
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public ResponseEntity<GroupDetailsModel> createGroup (GroupRequestModel request) {

        GroupDetailsModel groupDetailsModel = null;

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setGroupName(request.getGroupName());
        groupEntity.setDescription(request.getDescription());

        groupRepository.save(groupEntity);
        groupDetailsModel = modelMapper.map(groupEntity, GroupDetailsModel.class);

        return new ResponseEntity<>(groupDetailsModel, HttpStatus.OK);

    }

}
