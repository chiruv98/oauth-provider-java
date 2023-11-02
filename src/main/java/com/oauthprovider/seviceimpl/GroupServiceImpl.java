package com.oauthprovider.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.oauthprovider.services.UserGroupService;

import ch.qos.logback.classic.Logger;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import com.oauthprovider.models.GroupDetailsModel;
import com.oauthprovider.models.GroupRequestModel;
import com.oauthprovider.entities.GroupEntity;
import com.oauthprovider.repositories.GroupRepository;

/* 
 * @author Chiranjeevi
*/

@Service
@Slf4j
public class GroupServiceImpl implements UserGroupService {
 
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public ResponseEntity<GroupDetailsModel> createGroup (GroupRequestModel request) {

        /* if (request.getGroupName() == null || request.getGroupName() == "") {
            return new ResponseEntity<>(groupDetailsModel, HttpStatus.BAD_REQUEST);
        } */

        GroupDetailsModel groupDetailsModel = null;

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setGroupName(request.getGroupName());
        groupEntity.setDescription(request.getDescription());

        groupRepository.save(groupEntity);
        groupDetailsModel = modelMapper.map(groupEntity, GroupDetailsModel.class);

        return new ResponseEntity<>(groupDetailsModel, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<GroupDetailsModel> getGroup (String id) {

        log.info("id: {}", id);

        GroupDetailsModel groupDetailsModel = null;

        Optional<GroupEntity> response = groupRepository.findById(id);
        groupDetailsModel = modelMapper.map(response, GroupDetailsModel.class);

        return new ResponseEntity<>(groupDetailsModel, HttpStatus.OK);
    }

}
