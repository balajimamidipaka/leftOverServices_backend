package com.leftoverlove.rest.webservices.leftoverlove_services.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

	boolean existsByEmail(String email);
}
