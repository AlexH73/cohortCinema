package com.example.cinema.service.auth;

import com.example.cinema.model.user.AbstractUser;
import com.example.cinema.repository.user.IUserRepository;
import com.example.cinema.util.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IUserRepository userRepository; //

    @Autowired
    private PasswordUtils passwordUtils;

    public boolean authenticate(String userLogin, String password) {
        AbstractUser user = userRepository.findByUserLogin(userLogin);

        if (user == null) {
            return false;
        }

        return passwordUtils.checkPassword(password, user.getPassword());
    }
}
