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
@Table(name="Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer productId;

    @Column(nullable = false)
	private String nameProduct;

	@Column(nullable = false)
	private Integer costProduct;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoryProduct", referencedColumnName = "categoryProductsName")
    private CategoryProduct categoryProduct;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "organizationName", referencedColumnName = "organizationName")
    private Organization organizationName;
}
