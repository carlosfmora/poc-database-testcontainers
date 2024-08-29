package com.example.pocdatabasetestcontainers.adapter.out;

import com.example.pocdatabasetestcontainers.application.port.out.CountCustomersPort;
import com.example.pocdatabasetestcontainers.testcontainers.SharedPostgreSqlContainerExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SharedPostgreSqlContainerExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerJpaAdapterIntegrationTest {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;
    private CountCustomersPort countCustomersPort;

    @BeforeEach
    void setup(){
        this.customerJpaRepository.deleteAll();
        this.countCustomersPort = new CustomerJpaAdapter(this.customerJpaRepository);
    }

    @Test
    void shouldReturnZeroRecordsWhenDatabaseIsEmpty() {
        Integer recordCount = this.countCustomersPort.countCustomers();
        assertEquals(0, recordCount, "Expected zero records in the database when it is clean");
    }

    @Test
    void shouldReturnOneWhenDatabaseHasOneRecord(){
        CustomerEntity customer = new CustomerEntity();
        customer.setId(34L);
        customer.setName("nameTest");
        this.customerJpaRepository.save(customer);

        Integer recordCount = this.countCustomersPort.countCustomers();

        assertEquals(1, recordCount, "Expected one record in the database");
    }
}
