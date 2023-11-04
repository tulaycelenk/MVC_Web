package com.rungroup.web.services;

import com.rungroup.web.dtos.RegistrationDto;
import com.rungroup.web.models.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
    User findByUsername(String username);
}
