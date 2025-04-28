package com.cinema.service.auth;

import com.cinema.model.user.AbstractUser;
import com.cinema.repository.user.IUserRepository;
import com.cinema.util.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IUserRepository userRepository; //

    @Autowired
    private PasswordUtils passwordUtils;

    public boolean authenticate(String userLogin, String password) {
        AbstractUser user = userRepository.findByUsername(userLogin);

        if (user == null) {
            return false;
        }

        return passwordUtils.checkPassword(password, user.getPassword());
    }
}
