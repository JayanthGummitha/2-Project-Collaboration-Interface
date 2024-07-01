package com.collaboration_interface.service;

import com.collaboration_interface.modal.User;

public interface UserService {

    User findUserProfileByJwt(String jwt)throws Exception;

    User findUserByEmail(String email)throws Exception;

    User findUserById(Long userId)throws Exception;

    User updatedUserProjectSize(User user, int number);


}
