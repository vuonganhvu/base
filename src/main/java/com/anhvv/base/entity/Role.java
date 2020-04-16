package com.anhvv.base.entity;

import com.anhvv.base.common.RoleType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
