package com.example.pocdatabasetestcontainers.testcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SharedPostgreSqlContainerExtension.class)
@ExtendWith(ContainerInstanceValidator.class)
public class FirstPostgresInstanceSharingTest {

    @Test
    void whenTestStartThenPostgreSqlContainerIsRunning() {
        Assertions.assertTrue(SharedPostgreSqlContainerExtension.containerIsRunning());
    }

    @Test
    void verifyContainerShared() {
        Assertions.assertTrue(ContainerInstanceValidator
            .verifyContainerId(SharedPostgreSqlContainerExtension.containerId())
        );
    }

}
