package com.example.springdataqbe;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mtumilowicz on 2018-08-13.
 */
@Entity
@Value
@Builder
@ToString
public class Address {
    @Id
    Integer id;
    String city;
}
