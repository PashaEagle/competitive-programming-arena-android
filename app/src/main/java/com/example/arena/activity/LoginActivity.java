package com.example.arena.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arena.R;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;

import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private CoreCommunicationService coreCommunicationService;

    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = (EditText) findViewById(R.id.editLoginUsername);
        editPassword = (EditText) findViewById(R.id.editLoginPassword);

        this.coreCommunicationService = new CoreCommunicationService(LoginActivity.this);
    }

    public void onButtonLoginClick(View v) throws JSONException {

        String loginString = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        UserDto userDto = coreCommunicationService.sendLoginRequest(loginString, password);
        if (userDto == null)
            return;

        UserSession.loggedUser = userDto;
        Intent intent = new Intent(".MainActivity");
        startActivity(intent);
        Toast.makeText(LoginActivity.this, "\uD83D\uDC4B\uD83C\uDFFC Hi again, " + UserSession.loggedUser.getFullName(), Toast.LENGTH_SHORT).show();
    }
}
