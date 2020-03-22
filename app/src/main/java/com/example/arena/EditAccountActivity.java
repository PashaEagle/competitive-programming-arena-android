package com.example.arena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.arena.constant.Role;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;

import org.json.JSONException;

public class EditAccountActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;

    private EditText editUsername;
    private EditText editPassword;
    private EditText editFullname;
    private EditText editAge;
    private EditText editGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        this.coreCommunicationService = new CoreCommunicationService(EditAccountActivity.this);

        editUsername = findViewById(R.id.editRegisterUsername);
        editPassword = findViewById(R.id.editRegisterPassword);
        editFullname = findViewById(R.id.editFullname);
        editAge = findViewById(R.id.editRegisterAge);
        editGroup = findViewById(R.id.editRegisterGroup);
    }

    public void onButtonSaveChangesClick(View v) throws JSONException {

        String url = getString(R.string.update_account_data_url);

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String fullName = editFullname.getText().toString();
        Integer age = Integer.parseInt(editAge.getText().toString());
        String group = editGroup.getText().toString();

        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .age(age)
                .group(group)
                .fullName(fullName)
                .role(Role.ROLE_USER)
                .build();

        UserDto userDto = coreCommunicationService.sendLoginRequest(url, username, password);
        if (userDto == null)
            return;


        Intent intent = new Intent(".EditAccountActivity");
        startActivity(intent);
    }
}
