package org._07_automappingobjectsexercise.util;

import org._07_automappingobjectsexercise.service.dto.UserLoginDTO;
import org._07_automappingobjectsexercise.service.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    String registerUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    String logout();
}
