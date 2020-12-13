package com.example.CourseWork.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="RoleUsers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idrole;

    @Column(nullable = false,unique = true)
    private String rolename;

}
