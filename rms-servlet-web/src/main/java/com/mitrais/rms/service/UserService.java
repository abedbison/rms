package com.mitrais.rms.service;

import com.mitrais.rms.dao.Dao;
import com.mitrais.rms.model.User;

import java.util.Optional;

public interface UserService extends Service<User, Long> {
    /**
     * Find {@link User} by its username
     * @param userName username
     * @return user
     */
    Optional<User> findByUserName(String userName);
}

