package de.novatec.bpm;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.camunda.community.migration.adapter.EnableCamunda7Adapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@EnableCamunda7Adapter
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
