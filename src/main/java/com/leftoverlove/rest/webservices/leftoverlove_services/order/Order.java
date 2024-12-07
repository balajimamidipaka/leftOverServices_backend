package com.leftoverlove.rest.webservices.leftoverlove_services.order;

import java.time.LocalDateTime;

import com.leftoverlove.rest.webservices.leftoverlove_services.food.Food;
import com.leftoverlove.rest.webservices.leftoverlove_services.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="order_table")
public class Order {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Food food;
	
	private LocalDateTime orderTimeStamp;
	
	public Order() {
		this.orderTimeStamp = LocalDateTime.now();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public LocalDateTime getOrderTimeStamp() {
		return orderTimeStamp;
	}
	public void setOrderTimeStamp(LocalDateTime orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}
	
}
