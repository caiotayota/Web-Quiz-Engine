package com.caiotayota.engine.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @NotBlank
    @Email(regexp = ".+@.+\\..+", message = "Email is not valid.")
    private String email;
    
    @Size(min = 5, message = "The password must have at least 5 characters.")
    private String password;
    
}
