package com.leftoverlove.rest.webservices.leftoverlove_services.food;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.leftoverlove.rest.webservices.leftoverlove_services.order.Order;
import com.leftoverlove.rest.webservices.leftoverlove_services.user.User;

@RestController
public class FoodResource {
	private FoodRepository frepo;
	public FoodResource(FoodRepository frepo) {
		this.frepo=frepo;
	}
	
	@GetMapping("/foods")
	public List<Food> retrieveAllFood() {
		return frepo.findAll();
	}
	
	@GetMapping("/foods/{id}")
	public Food retrieveFoodWithId(@PathVariable int id) {
		return frepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND, "Food not found with id " + id
	                ));
	}
	
	@GetMapping("/foods/{id}/donor")
	public DonorDTO retrieveDonor(@PathVariable int id) {
		Food food=frepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND, "Food not found with id " + id
	                ));
		User user= food.getDonor();
		return new DonorDTO(user.getId(),user.getName(),user.getEmail(),user.getAddress());
	}
	
	@GetMapping("/foods/{id}/orders")
	public List<Order> retrieveFoodOrders(@PathVariable int id){
		Food food=frepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND, "Food not found with id " + id
	                ));
		return food.getOrders();
	}
	
}
