package org.example.productservice5_cached.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    //private Set<Role> roles = new HashSet<>();
}
