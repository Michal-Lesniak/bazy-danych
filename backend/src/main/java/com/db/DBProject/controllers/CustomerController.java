package com.db.DBProject.controllers;

import com.db.DBProject.dto.CustomerDto;
import com.db.DBProject.models.Customer;
import com.db.DBProject.repositories.CustomerRepository;
import com.db.DBProject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtoList = customerService.findAll().stream().map(customerService::mapToDto).toList();
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping(value = "/customers/freeCode")
    public ResponseEntity<Integer> getFirstFreeCode(){
        return ResponseEntity.ok().body(customerService.findFirstFreeCustomerCode());
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerBody) {
            try {
                Customer customer = customerService.addCustomer(customerService.mapToCustomer(customerBody));
                return ResponseEntity.ok().body(customerService.mapToDto(customer));
            } catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
    }

    @DeleteMapping("/customers/{customer_code}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable(value = "customer_code") Integer customerCode) {
        Customer customer = customerService.findOne(customerCode);
        if (customer != null) {
            customerService.deleteCustomer(customer);
            return ResponseEntity.ok().body(true);
        } else return ResponseEntity.badRequest().build();
    }
}
