package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private final RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";
    private String sessionID = null;
    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
        });
        sessionID = responseEntity.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        return responseEntity.getBody();
    }

    public HttpHeaders createHTTPHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionID);
        return headers;
    }

    public void saveUser(User user) {
        HttpEntity<User> requestEntity = new HttpEntity<>(user, createHTTPHeader());
        Long id = user.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity,
                    String.class);
            System.out.println("User was added!");
            System.out.println(responseEntity.getBody());
        }
        else {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity,
                    String.class);
            System.out.println("User was edited!");
        }
    }

    public void updateUser(User user) {

    }

    public void deleteUser(Long id) {

    }
}