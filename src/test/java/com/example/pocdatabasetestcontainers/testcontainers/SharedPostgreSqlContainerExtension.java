package com.example.pocdatabasetestcontainers.testcontainers;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class SharedPostgreSqlContainerExtension implements BeforeAllCallback, AfterAllCallback {

    private static PostgreSQLContainer<?> postgresContainer;

    public static boolean containerIsRunning() {
        return postgresContainer.isRunning();
    }

    public static String containerId() {
        return postgresContainer.getContainerId();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (postgresContainer != null) {
            return;
        }
        postgresContainer = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("databaseName")
            .withUsername("username")
            .withPassword("password")
            .withInitScript("initScript.sql");

        postgresContainer.start();
        replaceSpringPropertiesForDatasource();
    }

    private void replaceSpringPropertiesForDatasource() {
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        // do nothing, Testcontainers handles container shutdown (Ryuk container)

    }
}
