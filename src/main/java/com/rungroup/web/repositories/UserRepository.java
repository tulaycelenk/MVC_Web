package com.rungroup.web.repositories;

import com.rungroup.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByUsername(String username);
}
