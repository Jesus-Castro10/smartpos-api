package com.educastro.sales.service;

import com.educastro.sales.model.dto.UserDTO;
import com.educastro.sales.model.entities.Role;
import com.educastro.sales.model.entities.User;
import com.educastro.sales.model.enums.RoleName;
import com.educastro.sales.repository.RoleRepository;
import com.educastro.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User create(UserDTO userDTO) {
        User user = new User();
        // user.setUsername(createUsername(userDTO));
        // user.setRoles(loadRoles(userDTO));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

    public User create(String name, String lastname, String role){
        User user = new User();
        user.setUsername(createUsername(name, lastname));
        List<Role> roles = loadRoles(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode("123456789"));
        return user;
    }

    private List<Role> loadRoles(String role){
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleUser = roleRepository.findByRolename(RoleName.ROLE_USER);
        roleUser.ifPresent(roles::add);
        Optional<Role> roleOptional = roleRepository.findByRolename(RoleName.valueOf(role));
        roleOptional.ifPresent(roles::add);
        return roles;
    }

    private String createUsername(String name, String lastname) {
        String username;
        Random random = new Random();
        int n = random.nextInt(32, 127);
        char letter = (char) n;

        username = lastname.charAt(0) + name + lastname.charAt(lastname.length() - 1)
                + letter + "." + random.nextInt(1,99);
        return username.toLowerCase();
    }
}
