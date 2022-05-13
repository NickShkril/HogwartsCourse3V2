package ru.hogwarts.schoolv2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HogwartsCourse3V2Application {

    public static void main(String[] args) {
        SpringApplication.run(HogwartsCourse3V2Application.class, args);
    }

}
