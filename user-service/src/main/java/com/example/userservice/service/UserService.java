package com.example.userservice.service;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;

    public void createUser(UserRequest userRequest){
        User user= new User();
        user.setName(userRequest.getName());
        user.setEmailId(userRequest.getEmailId());

        userRepository.save(user);
        log.info("Customer {} is Saved ", user.getId());
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .emailId(user.getEmailId()).build();
    }

    public void deleteUser(String id) throws UserNotFoundException {
        if(userRepository.existsById(Long.getLong(id))){
            userRepository.deleteById(Long.getLong(id));
        }
        else{
            throw new UserNotFoundException(id);
        }
    }
}
