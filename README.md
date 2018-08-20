[![Build Status](https://travis-ci.com/mtumilowicz/springdata-qbe.svg?branch=master)](https://travis-ci.com/mtumilowicz/springdata-qbe)

# springdata-qbe
The main goal of this project is to explore basics of `Query By Example` API in 
`Spring Data` environment.

_Reference_: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example

# manual
1. matching with non null fields
    ```
    Customer customer = // init pattern fields
    
    Example<Customer> customerExample = Example.of(customer);
    
    customerRepository.findOne(customerExample)
    ```

1. matching with null fields (remember to exclude `id` field - if it stays the result 
will be empty)
    ```
    ExampleMatcher matcher = matching()
            .withIncludeNullValues()
            .withIgnorePaths("id"); // and other fields that we don't want to have null in result
    
    Customer customer = // init pattern fields
    
    Example<Customer> customerExample = Example.of(customer, matcher);
    
    customerRepository.findOne(customerExample)
    ```
    
1. matching with:
    * ignoring case
    * starts with
    * ends with
    
    ```
    ExampleMatcher matcher = matching()
                    .withIgnoreCase()
                    .withMatcher("firstName", GenericPropertyMatcher::startsWith)
                    .withMatcher("lastName", GenericPropertyMatcher::endsWith);
    
    Customer customer = Customer.builder()
                    .firstName("m")
                    .lastName("wicz")
                    .build();
    
    Example<Customer> customerExample = Example.of(customer, matcher);
            
    customerRepository.findOne(customerExample);
    ```