package com.db.DBProject.services;

import  com.db.DBProject.dto.CustomerDto;
import com.db.DBProject.models.Customer;
import com.db.DBProject.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findOne(Integer customerCode) {
        Optional<Customer> customer = customerRepository.findByUserCode(customerCode);
        return customer.orElse(null);
    }

    public Integer findFirstFreeCustomerCode(){
        List<Customer> customers = customerRepository.findAll();
        Set<Integer> codes = customers.stream().map(Customer::getUserCode).collect(Collectors.toSet());

        for(int i = 1; i < Collections.max(codes); i++){
            if(!codes.contains(i)){
                return i;
            }
        }

        return Collections.max(codes) + 1;
    }

    @Transactional
    public Customer addCustomer(Customer customerBody) throws Exception {
        Optional<Customer> customer = customerRepository.findByUserCode(customerBody.getUserCode());
        if (customer.isEmpty()) {
            return customerRepository.save(customerBody);
        } else throw new Exception("Użytkownik istnieje");
    }

    @Transactional
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer mapToCustomer(CustomerDto customerDto) {
            Customer customer = new Customer();
            customer.setUserCode(customerDto.userCode());
            customer.setName(customerDto.name());
            customer.setEmail(customerDto.email());
            customer.setPhone(customerDto.phone());
            return customer;
    }

    public CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(
                customer.getUserCode(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
