package de.novatec.bpm.adapters.external.rest;

import de.novatec.bpm.ports.StudentOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

@Component
public class StudentRestClient implements StudentOutputPort {
    private final RestTemplate restTemplate;

    public StudentRestClient() {
        this.restTemplate = new RestTemplate();
    }

    @Value( "${student-rest-client.url}" )
    private String url;
    @Override
    public boolean checkIfStudentExists(String email) {
        try{
            var response = restTemplate.getForObject("%s/%s".formatted(url, email), Map.class);
            return response != null && response.containsKey("email") && response.get("email") != null;
        } catch (Exception ex){
            //Just for testing-purpose if the Endpoint is not available or the wrong Endpoint is requested
            return true;
        }
    }
}


