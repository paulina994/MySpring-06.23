package com.example.springdataaccounts;

import com.example.springdataaccounts.services.AccountService;
import com.example.springdataaccounts.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AccountService accountService;

    private final UserService userService;


    public ConsoleRunner(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
//        User someUser = new User("someUser", 20);
//        userService.register(someUser);

        accountService.withdrawMoney(BigDecimal.valueOf(200), 1L);
    }
}