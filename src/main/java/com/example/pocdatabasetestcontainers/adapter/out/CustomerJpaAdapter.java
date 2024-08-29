package com.example.pocdatabasetestcontainers.adapter.out;

import com.example.pocdatabasetestcontainers.application.port.out.CountCustomersPort;
import org.springframework.stereotype.Component;

@Component
public class CustomerJpaAdapter implements CountCustomersPort {
    private final CustomerJpaRepository customerJpaRepository;

    public CustomerJpaAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public int countCustomers() {
        return this.customerJpaRepository.findAll().size();
    }
}
