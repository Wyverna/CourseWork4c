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
@Table(name="ExtraProducts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraProduct implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer extraProductId;

    @Column(nullable = false, unique = true)
	private String nameExtraProduct;

	@Column(nullable = false)
	private Integer unitCost;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "nameProduct", referencedColumnName = "nameProduct")
    private Product nameProduct;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "organizationName", referencedColumnName = "organizationName")
    private Organization organizationName;
}
