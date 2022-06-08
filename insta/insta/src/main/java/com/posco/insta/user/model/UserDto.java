package com.posco.insta.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Data
public class UserDto {
    private Integer id;
    private String password;
    private String userId;
    private String img;
    private String name;
}
