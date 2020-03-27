package com.example.arena.integration;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import com.example.arena.R;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.dto.LoginHttpRequest;
import com.example.arena.integration.dto.RegisterHttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public UserDto sendLoginRequest(String loginString, String password) {

        String url = context.getString(R.string.login_url);
        LoginHttpRequest request = LoginHttpRequest.builder()
                .loginString(loginString)
                .password(password)
                .build();
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

    @SneakyThrows
    public boolean sendRegisterRequest(UserDto userDto) {

        String url = context.getString(R.string.register_url);
        RegisterHttpRequest request = RegisterHttpRequest.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .age(userDto.getAge())
                .fullName(userDto.getFullName())
                .group(userDto.getGroup())
                .build();
        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
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
            return false;
        }
    }

    @SneakyThrows
    public boolean checkUserExistsByUsernameRequest(String username) {

        String url = context.getString(R.string.check_username_exists_url) + "/" + username;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error when sending check user exists by username request. Message = " + e.getMessage());
            Toast.makeText(context, "Error when sending request to check user exists..", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void sendUpdateUserInfoRequest(UserDto userDto) {

        String url = context.getString(R.string.update_account_data_url);
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Error when sending update user info request. Message = " + e.getMessage());
        }
    }

    public boolean checkCodeforcesUsernameExists(String username) {

        String url = context.getString(R.string.check_codeforces_username_url) + "/" + username;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            if (response.getBody().equals(false))
                Toast.makeText(context, "This codeforces handle not exist", Toast.LENGTH_SHORT).show();
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error when sending check codeforces username exists request. Message = " + e.getMessage());
            Toast.makeText(context, "Error when sending request to check codeforces username exists..", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
