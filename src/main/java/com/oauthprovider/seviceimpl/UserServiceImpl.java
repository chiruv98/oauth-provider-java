package com.oauthprovider.seviceimpl;

import com.oauthprovider.services.UserService;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oauthprovider.entities.GroupEntity;
import com.oauthprovider.entities.UserEntity;
import com.oauthprovider.exception.ErrorResponse;
import com.oauthprovider.exception.GlobalExceptionHandler;
import com.oauthprovider.models.UserDetailsModel;
import com.oauthprovider.models.UserRequestModel;
import com.oauthprovider.repositories.GroupRepository;
import com.oauthprovider.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GlobalExceptionHandler exceptionHandler;

    @Override
    public Object createUser (String groupId, UserRequestModel request) {

        UserDetailsModel userDetailsModel = null;

        HashMap<String, String> userIdAndEmail = checkUserEmail(request.getEmail());

        if(!userIdAndEmail.isEmpty()) {

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String currentUri = attr.getRequest().getRequestURI().toString();
            List<String> message = new ArrayList<String>();
            message.add("Duplicate Email, email already exists");
            ErrorResponse errorResponse = exceptionHandler.handleCustomException(
                400, 
                "1234", 
                message, 
                "BAD_REQUEST",
                currentUri);
            return errorResponse;

        }

        UserEntity userEntity = modelMapper.map(request, UserEntity.class);
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(() ->new EntityNotFoundException("No data found for given group id"));
        userEntity.setGroup(group);
        userRepository.save(userEntity);

        userDetailsModel = modelMapper.map(userEntity, UserDetailsModel.class);

        return userDetailsModel;
    }

    @Override
    public List<UserDetailsModel> listUsers (String groupId) {
        List<UserEntity> userEntity = (List<UserEntity>) userRepository.findByGroupId(groupId).orElseThrow(() -> new EntityNotFoundException("No data found for given group id"));
        List<UserDetailsModel> userDetails = new ArrayList<>();
        for (UserEntity user : userEntity) {
            userDetails.add((UserDetailsModel) modelMapper.map(user, UserDetailsModel.class));
        };
        return userDetails;
    }

    private HashMap<String, String> checkUserEmail (String email) {
        HashMap<String, String> userIdAndEmail = new HashMap<>();

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity response = userEntity.get();
            userIdAndEmail.put(response.getId(), response.getEmail());
        }

        return userIdAndEmail;

    }
    
}
