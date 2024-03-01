package com.example.xmlex.services;

import com.example.xmlex.modules.dtos.UserSeedDto;
import com.example.xmlex.modules.dtos.UserViewRootDto;
import com.example.xmlex.modules.entities.User;
import com.example.xmlex.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public interface UserService {

    long getCount();

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();


    UserViewRootDto findUsersWithMoreThanOneSoldProduct();
}
