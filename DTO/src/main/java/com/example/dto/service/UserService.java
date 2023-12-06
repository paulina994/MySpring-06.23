package com.example.dto.service;


import com.example.dto.model.entitiy.User;
import com.example.dto.model.entitiy.dto.OwnedGamesDto;
import com.example.dto.model.entitiy.dto.UserLoginDto;
import com.example.dto.model.entitiy.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    List<OwnedGamesDto> ownedGames(User loggedInUser);
}