package com.convert.romannumeral;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mockStatic;

/**
 * @author dvengambhanumoorthy
 */
@ExtendWith(MockitoExtension.class)
class RomanNumberApplicationTest {
    final String[] args = {"TEST"};
    @InjectMocks
    RomanNumberApplication romanNumberApplication;
    @Mock
    ConfigurableApplicationContext configurableApplicationContext;
    @Mock
    SpringApplication springApplication;

    @Test
    void appMain() {
        try (MockedStatic<SpringApplication> mockedStatic = mockStatic(SpringApplication.class,
                "run")) {
            mockedStatic
                    .when(() -> SpringApplication.run(ArgumentMatchers.any(Class.class),
                            ArgumentMatchers.eq(args))).thenReturn(this.configurableApplicationContext);
            RomanNumberApplication.main(args);
            Assertions.assertNotNull(this.configurableApplicationContext);
        }
    }
}

