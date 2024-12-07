package com.leftoverlove.rest.webservices.leftoverlove_services.user;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.leftoverlove.rest.webservices.leftoverlove_services.exception.ErrorDetails;
import com.leftoverlove.rest.webservices.leftoverlove_services.exception.UserNotFoundException;
import com.leftoverlove.rest.webservices.leftoverlove_services.food.Food;
import com.leftoverlove.rest.webservices.leftoverlove_services.food.FoodRepository;
import com.leftoverlove.rest.webservices.leftoverlove_services.order.Order;
import com.leftoverlove.rest.webservices.leftoverlove_services.order.OrderRepository;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserRepository urepo;
	private FoodRepository frepo;
	private OrderRepository orepo;
	public UserResource(UserRepository urepo,FoodRepository frepo,OrderRepository orepo) {
		this.urepo=urepo;
		this.frepo=frepo;
		this.orepo=orepo;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return urepo.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		if(urepo.existsByEmail(user.getEmail())) {
			ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),"user with this email already exists","uri=/users");
			return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		}
		urepo.save(user);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		 Optional<User> user = urepo.findById(id);
	        
	     return user.orElseThrow(() -> new UserNotFoundException("id "+id));
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		urepo.deleteById(id);
	}
	
	@GetMapping("/users/{id}/donations")
	public List<Food> retrieveDonations(@PathVariable int id){
		User user = urepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id "+id));
		return user.getDonations();
	}
	
	@PostMapping("/users/{id}/donations")
	public ResponseEntity<Food> donateFood(@PathVariable int id,@RequestBody @Valid Food food){
		User user = urepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id "+id));
		food.setDonor(user);
		frepo.save(food);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/users/{id}/orders")
	public List<Order> retrieveUserOrders(@PathVariable int id){
		User user=urepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("id "+id));
		return user.getOrders();
	}
	
	@PostMapping("/users/{id}/orders")
	public ResponseEntity<Object> orderFood(@PathVariable int id,@RequestBody FoodId foodr){
		int fid=foodr.getId();
		User user=urepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("id "+id));
		Food food=frepo.findById(fid)
				.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.NOT_FOUND,"Food not found with id "+fid
						));
		Order order=new Order();
		order.setUser(user);
		order.setFood(food);
		orepo.save(order);
		user.getOrders().add(order);
		urepo.save(user);
		return ResponseEntity.created(null).build();
	}
}
