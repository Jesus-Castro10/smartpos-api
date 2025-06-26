package com.educastro.sales.service;

import com.educastro.sales.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findByUsername(String username);
}
