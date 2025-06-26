package com.educastro.sales.repository;

import com.educastro.sales.model.entities.Role;
import com.educastro.sales.model.enums.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRolename(RoleName rolename);
}
