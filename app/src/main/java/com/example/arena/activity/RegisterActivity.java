package com.example.arena.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arena.R;
import com.example.arena.constant.Role;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;

    private TextInputLayout textInputRegisterEmail;
    private TextInputLayout textInputRegisterUsername;
    private TextInputLayout textInputRegisterFullname;
    private TextInputLayout textInputRegisterPassword;
    private TextInputLayout textInputRegisterAge;
    private Spinner spinnerRegisterGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.coreCommunicationService = new CoreCommunicationService(RegisterActivity.this);

        textInputRegisterEmail = findViewById(R.id.textInputRegisterEmail);
        textInputRegisterUsername = findViewById(R.id.textInputRegisterUsername);
        textInputRegisterFullname = findViewById(R.id.textInputRegisterFullname);
        textInputRegisterPassword = findViewById(R.id.textInputRegisterPassword);
        textInputRegisterAge = findViewById(R.id.textInputRegisterAge);
        spinnerRegisterGroup = findViewById(R.id.spinnerRegisterGroup);
    }

    public void onButtonRegisterActivityClick(View v) {

        String email = textInputRegisterEmail.getEditText().getText().toString();
        String username = textInputRegisterUsername.getEditText().getText().toString();
        String fullName = textInputRegisterFullname.getEditText().getText().toString();
        String password = textInputRegisterPassword.getEditText().getText().toString();
        Integer age = Integer.parseInt(textInputRegisterAge.getEditText().getText().toString());
        String group = spinnerRegisterGroup.getSelectedItem().toString();

        UserDto userDto = UserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .age(age)
                .group(group)
                .fullName(fullName)
                .role(Role.ROLE_USER)
                .build();

        boolean success = coreCommunicationService.sendRegisterRequest(userDto);

        if (success) {
            Toast.makeText(this, "Registration success. You can now login ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(".LoginActivity");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error when trying to register.. Try later ", Toast.LENGTH_SHORT).show();
        }
    }
}
