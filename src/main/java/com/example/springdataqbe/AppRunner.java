package com.example.springdataqbe;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Component
@AllArgsConstructor
@Slf4j
public class AppRunner implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        System.out.println(customerRepository.findAll());

        Address address = Address.builder()
                .city("Warsaw")
                .build();

        Customer customer = Customer.builder()
                .address(address)
                .build();

        Example<Customer> customerExample = Example.of(customer);

        System.out.println("all");
        System.out.println(customerRepository.findAll(customerExample));

        customer = Customer.builder()
                .firstName("Slawomir")
                .build();

        customerExample = Example.of(customer);

        System.out.println("Slawomir");
        System.out.println(customerRepository.findAll(customerExample));

        ExampleMatcher matcher = matching()
                .withIgnoreCase()
                .withMatcher("firstName", GenericPropertyMatcher::startsWith)
                .withMatcher("lastName", GenericPropertyMatcher::endsWith);

        customer = Customer.builder()
                .firstName("m")
                .lastName("wicz")
                .build();

        customerExample = Example.of(customer, matcher);

        System.out.println("m - wicz");
        System.out.println(customerRepository.findAll(customerExample));

        matcher = matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "lastName", "address");

        customer = Customer.builder()
                .build();

        customerExample = Example.of(customer, matcher);

        System.out.println("without firstName");
        System.out.println(customerRepository.findAll(customerExample));
    }

}
