package com.anhvv.base.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anhvv.base.common.RoleType;
import com.anhvv.base.entity.converter.ListConverter;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String password;

    @Convert(converter = ListConverter.class)
    private List<RoleType> userRole;

    public User() {
    }

    public User(String userName, String password, List<RoleType> userRole) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public boolean match(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }
}
