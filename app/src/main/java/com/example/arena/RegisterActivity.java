package com.example.arena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arena.constant.Role;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;

    private EditText editRegisterUsername;
    private EditText editRegisterPassword;
    private EditText editRegisterFullname;
    private EditText editRegisterAge;
    private EditText editRegisterGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.coreCommunicationService = new CoreCommunicationService(RegisterActivity.this);

        editRegisterUsername = findViewById(R.id.editRegisterUsername);
        editRegisterPassword = findViewById(R.id.editRegisterPassword);
        editRegisterFullname = findViewById(R.id.editRegisterFullname);
        editRegisterAge = findViewById(R.id.editRegisterAge);
        editRegisterGroup = findViewById(R.id.editRegisterGroup);
    }

    public void onButtonRegisterActivityClick(View v) {

        String url = getString(R.string.register_url);
        String username = editRegisterUsername.getText().toString();
        String password = editRegisterPassword.getText().toString();
        String fullName = editRegisterFullname.getText().toString();
        Integer age = Integer.parseInt(editRegisterAge.getText().toString());
        String group = editRegisterGroup.getText().toString();

        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .age(age)
                .group(group)
                .fullName(fullName)
                .role(Role.ROLE_USER)
                .build();

        boolean success = coreCommunicationService.sendRegisterRequest(url, userDto);

        if (success) {
            Toast.makeText(this, "Registration success. You can now login ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(".LoginActivity");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error when trying to register.. Try later ", Toast.LENGTH_SHORT).show();
        }
    }
}
