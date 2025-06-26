package com.educastro.sales.model.entities;

import com.educastro.sales.model.enums.RoleName;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName rolename;

    public Role(RoleName name) {
        this.rolename = name;
    }
}
