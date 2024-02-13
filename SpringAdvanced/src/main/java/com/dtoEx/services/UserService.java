package com.dtoEx.services;

import com.dtoEx.models.dto.UserLoginDto;
import com.dtoEx.models.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();
}
