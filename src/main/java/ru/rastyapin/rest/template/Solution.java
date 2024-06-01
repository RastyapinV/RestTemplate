package ru.rastyapin.rest.template;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.rastyapin.rest.template.models.User;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        final String url = "http://94.198.50.185:7081/api/users";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> cookies = responseHeaders.get("Set-Cookie");
        String sessionId = null;
        if (cookies != null && !cookies.isEmpty())
            for (String cookie : cookies)
                sessionId = cookie;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);

        User user = new User(3L, "James", "Brown", (byte) 30);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String responseCode = restTemplate.postForEntity(url, entity, String.class).getBody();
        
        user.setName("Thomas");
        user.setLastName("Shelby");
        responseCode += restTemplate.exchange(url, HttpMethod.PUT, entity, String.class).getBody();

        responseCode += restTemplate.exchange(url + "/3", HttpMethod.DELETE, entity
                , String.class).getBody();

        System.out.println(responseCode);
    }

}
