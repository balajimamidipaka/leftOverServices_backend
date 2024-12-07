package com.leftoverlove.rest.webservices.leftoverlove_services.food;

import java.time.LocalDate;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leftoverlove.rest.webservices.leftoverlove_services.order.Order;
import com.leftoverlove.rest.webservices.leftoverlove_services.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Food {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message="name cannot be blank")
	private String name;
	private String image;
	
	@NotNull(message="quantity cannot be blank")
	@Positive(message="quantity cannot be a negative value")
	private Integer quantity;
	
	@FutureOrPresent(message="expiry date cannot be in the past")
	private LocalDate expirydate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User donor;
	
	@OneToMany(mappedBy="food",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Order> orders=new ArrayList<>();
	
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
	public LocalDate getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(LocalDate expirydate) {
		this.expirydate = expirydate;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public User getDonor() {
		return donor;
	}
	public void setDonor(User donor) {
		this.donor = donor;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", expirydate=" + expirydate + "]";
	}
	
}
