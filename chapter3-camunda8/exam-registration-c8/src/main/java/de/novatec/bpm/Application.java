package de.novatec.bpm;

import org.camunda.community.migration.adapter.EnableCamunda7Adapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@EnableCamunda7Adapter
@Deployment( resources = { "classpath:/process/converted-c8-exam-registration.bpmn" , "classpath:/process/examRegistrationPeriod.dmn"})
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
