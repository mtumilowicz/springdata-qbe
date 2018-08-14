package com.example.springdataqbe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * Created by mtumilowicz on 2018-08-14.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryByExampleTest {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Test
    public void defaultMatcher_relationship() {
//        given
        Address address = Address.builder()
                .city("Warsaw")
                .build();

        Customer customer = Customer.builder()
                .address(address)
                .build();

        Example<Customer> customerExample = Example.of(customer);
        
//        expect
        assertThat(customerRepository.findOne(customerExample).orElseThrow(EntityNotFoundException::new).getId(), 
                is(1));
    }
    
    @Test
    public void defaultMatcher_field() {
//        given
        Customer customer = Customer.builder()
                .firstName("Slawomir")
                .build();

        Example<Customer> customerExample = Example.of(customer);
        
//        expect
        assertThat(customerRepository.findOne(customerExample).orElseThrow(EntityNotFoundException::new).getId(), 
                is(2));
    }
    
    @Test
    public void matcher_ignoreCase_startWith_endsWith_fields() {
//        given
        ExampleMatcher matcher = matching()
                .withIgnoreCase()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatcher::endsWith);

        Customer customer = Customer.builder()
                .firstName("m")
                .lastName("wicz")
                .build();

        Example<Customer> customerExample = Example.of(customer, matcher);
        
//        expect
        assertThat(customerRepository.findOne(customerExample).orElseThrow(EntityNotFoundException::new).getId(), 
                is(1));
    }
    
    @Test
    public void matcher_includeNullValues_ignorePaths_fields() {
//        given
        ExampleMatcher matcher = matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "lastName", "address");

        Customer customer = Customer.builder()
                .build();

        Example<Customer> customerExample = Example.of(customer, matcher);
        
//        expect
        assertThat(customerRepository.findOne(customerExample).orElseThrow(EntityNotFoundException::new).getId(),
                is(3));
    }
    
}
