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
@Table(name="CategoryCompanies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCompany implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer categoryCompanyId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "organizationName", referencedColumnName = "organizationName")
    private Organization organizationName;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoryProduct", referencedColumnName = "categoryProductsName")
    private CategoryProduct categoryProduct;
}
