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
import java.util.Date;

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer orderId;
	

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "nameProduct", referencedColumnName = "nameProduct")
    private Product nameProduct;
	

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "username", referencedColumnName = "loginuser")
    private User username;
	
	@Column(nullable = true)
	private Date dateOrder;
	
	@Column(nullable = true)
	private Date dateOrderEnd;
	
	@Column(nullable = false)
	private Integer unitCost;
	
	@Column(nullable = true)
	private String addressClient;


    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "organizationName", referencedColumnName = "organizationName")
    private Organization organizationName;
	
	@Column(nullable = true)
	private String timeToOrder;
	
	@Column(nullable = true)
	private String nameExtraProduct;
}
