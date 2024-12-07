package com.leftoverlove.rest.webservices.leftoverlove_services.user;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leftoverlove.rest.webservices.leftoverlove_services.food.Food;
import com.leftoverlove.rest.webservices.leftoverlove_services.order.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@Email(message="email should be valid")
	@NotBlank(message="email is mandatory")
	@Column(unique=true)
	private String email;
	
	private String address;
	
	@Size(min=5,message="password must be atleast 5 characters long")
	@NotBlank(message="password is mandatory")
	private String password;
	
	@OneToMany(mappedBy="donor")
	@JsonIgnore
	private List<Food> donations;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Order> orders=new ArrayList<>();
	
	public User() {
		
	}
	public User(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Food> getDonations() {
		return donations;
	}
	public void setDonations(List<Food> donations) {
		this.donations = donations;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
