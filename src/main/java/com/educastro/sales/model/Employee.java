package com.educastro.sales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jesus Castro
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String idCard;

    @NotBlank
    private String firstname;

    @NotBlank
    private String secondname;

    @NotBlank
    private String firstLastname;

    @NotBlank
    private String secondLastname;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    public String getFullName() {
        return this.firstname + " " + this.firstLastname;
    }

    public String getNames() {
        return this.firstname + " " + this.secondname;
    }

    public String getLastnames() {
        return this.firstLastname + " " + this.secondLastname;
    }

}
