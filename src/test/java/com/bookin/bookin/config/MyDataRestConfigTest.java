package com.bookin.bookin.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@ContextConfiguration(classes = {MyDataRestConfig.class})
@ExtendWith(SpringExtension.class)
class MyDataRestConfigTest {
    @Autowired
    private MyDataRestConfig myDataRestConfig;

    /**
     * Method under test: {@link MyDataRestConfig#configureRepositoryRestConfiguration(RepositoryRestConfiguration, CorsRegistry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfigureRepositoryRestConfiguration() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CorsRegistry.registrations

        // Arrange
        // TODO: Populate arranged inputs
        RepositoryRestConfiguration config = null;
        CorsRegistry cors = null;

        // Act
        this.myDataRestConfig.configureRepositoryRestConfiguration(config, cors);

        // Assert
        // TODO: Add assertions on result
    }
}

