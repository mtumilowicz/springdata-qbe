package com.example.springdataqbe;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by mtumilowicz on 2018-08-10.
 */
@Entity
@Value
@Builder
@ToString
public class Customer {
    @Id
    Integer id;
    String firstName;
    String lastName;
    @OneToOne
    Address address;
}
