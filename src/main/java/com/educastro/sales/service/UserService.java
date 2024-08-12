package com.educastro.sales.service;

import com.educastro.sales.model.dto.UserDTO;
import com.educastro.sales.model.entities.Employee;
import com.educastro.sales.model.entities.Role;
import com.educastro.sales.model.entities.User;
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
        user.setUsername(createUsername(userDTO));
        user.setRoles(loadRoles(userDTO));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

    private List<Role> loadRoles(UserDTO user){
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
        roleUser.ifPresent(roles::add);
        if (user.isAdmin()){
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleAdmin.ifPresent(roles::add);
        }
        if (user.isCashier()){
            Optional<Role> roleCashier = roleRepository.findByName("ROLE_CASH");
            roleCashier.ifPresent(roles::add);
        }
        if (user.isCustomer()){
            Optional<Role> roleCustomer = roleRepository.findByName("ROLE_CUSTOMER");
            roleCustomer.ifPresent(roles::add);
        }
        return roles;
    }

    private String createUsername(UserDTO userDTO) {
        String name, lastName;
        name = userDTO.getName();
        lastName = userDTO.getLastName();
        String username;

        Random random = new Random();
        int n = random.nextInt(32, 127);
        char letter = (char) n;

        username = lastName.charAt(0) + name + lastName.charAt(lastName.length() - 1)
                + letter + "." + random.nextInt(1,99);
        return username.toLowerCase();
    }
}
