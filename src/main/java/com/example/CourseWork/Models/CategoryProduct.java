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
@Table(name="CategoryProducts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProduct implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer categoryProductsId;
    @Column(nullable = false,unique = true)
    private String categoryProductsName;

}
