package com.example.arena.integration;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import com.example.arena.dto.UserDto;
import com.example.arena.integration.dto.LoginHttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;

public class CoreCommunicationService {

    private static final String LOGIN_TAG = "LOGIN";
    private final Context context;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    public CoreCommunicationService(Context context) {
        this.context = context;
        mapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @SneakyThrows
    public UserDto sendLoginRequest(String url, String username, String password) {

        LoginHttpRequest request = LoginHttpRequest.builder().username(username).password(password).build();
        try {
            ResponseEntity<UserDto> response = restTemplate.postForEntity(url, request, UserDto.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error when sending login request. Message = " + e.getMessage());
            if (e.getMessage().contains("400")) {
                Toast.makeText(context, "Invalid account name or password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error when sending request to login..", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}
