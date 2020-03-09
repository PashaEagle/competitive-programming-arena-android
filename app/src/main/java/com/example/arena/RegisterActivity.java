package com.example.arena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arena.constant.Role;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;

import java.time.Clock;
import java.time.LocalDateTime;

public class RegisterActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;

    private EditText editUsername;
    private EditText editPassword;
    private EditText editFullname;
    private EditText editAge;
    private EditText editGroup;
    private EditText editCodeforcesUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.coreCommunicationService = new CoreCommunicationService(RegisterActivity.this);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editFullname = findViewById(R.id.editFullname);
        editAge = findViewById(R.id.editAge);
        editGroup = findViewById(R.id.editGroup);
        editCodeforcesUsername = findViewById(R.id.editCFUsername);
    }

    public void onButtonRegisterActivityClick(View v){

        String url = getString(R.string.register_url);
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String fullname = editFullname.getText().toString();
        Integer age = Integer.parseInt(editAge.getText().toString());
        String group = editGroup.getText().toString();
        String codeforcesUsername = editCodeforcesUsername.getText().toString();

        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .age(age)
                .group(group)
                .fullName(fullname)
                .codeforcesUsername(codeforcesUsername)
                .role(Role.ROLE_USER)
                .build();

        boolean success = coreCommunicationService.sendRegistrationRequest(url, username, password);

        Toast.makeText(this, "Registration success. You can now login ", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(".LoginActivity");
        startActivity(intent);
    }
}
