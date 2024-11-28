package com.first.services;

import com.first.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean doLogin() {
        //login logic
        loginRepository.getUser();
        return true;
    }
}
