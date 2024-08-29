package com.example.pocdatabasetestcontainers.testcontainers;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ContainerInstanceValidator implements BeforeAllCallback {

    private static ContainerInstanceValidator instance;
    private static String containerId;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (instance == null) {
            instance = new ContainerInstanceValidator();
        }
    }

    public static boolean verifyContainerId(String containerId) {
        if (ContainerInstanceValidator.containerId == null) {
            ContainerInstanceValidator.containerId = containerId;
            return true;
        }
        return ContainerInstanceValidator.containerId.equals(containerId);
    }
}
