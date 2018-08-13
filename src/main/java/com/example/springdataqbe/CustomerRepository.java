package com.example.springdataqbe;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mtumilowicz on 2018-08-10.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
