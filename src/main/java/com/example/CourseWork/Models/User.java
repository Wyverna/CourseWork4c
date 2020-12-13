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
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String loginuser;

    @Column(nullable = false)
    private String passworduser;

    @Column(nullable = false)
    private String emailuser;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "roleuser", referencedColumnName = "rolename")
    private RoleUser roleuser;


}
