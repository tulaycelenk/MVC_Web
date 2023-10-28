package com.rungroup.web.services;

import com.rungroup.web.dtos.RegistrationDto;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
}
