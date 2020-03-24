package com.example.arena;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.arena.integration.CoreCommunicationService;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;


    private TextInputLayout textInputChangeUsername;
    private String newUsername;
    private TextInputLayout textInputChangeFullname;
    private String newFullname;
    private TextInputLayout textInputChangeAge;
    private Integer newAge;
    private Spinner spinnerChangeGroup;
    private String newGroup;

    private TextInputLayout textInputOldPassword;
    private String oldPassword;
    private TextInputLayout textInputNewPassword;
    private String newPassword;

    private TextInputLayout textInputChangeCodeforcesUsername;
    private String newCodeforcesUsername;
    private TextInputLayout textInputChangeCodewarsUsername;
    private String newCodewarsUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        this.coreCommunicationService = new CoreCommunicationService(EditAccountActivity.this);

        textInputChangeUsername = findViewById(R.id.textInputChangeUsername);
        textInputChangeFullname = findViewById(R.id.textInputChangeFullname);
        textInputChangeAge = findViewById(R.id.textInputChangeAge);
        spinnerChangeGroup = findViewById(R.id.spinnerChangeGroup);

        textInputOldPassword = findViewById(R.id.textInputOldPassword);
        textInputNewPassword = findViewById(R.id.textInputNewPassword);

        textInputChangeCodeforcesUsername = findViewById(R.id.textInputChangeCodeforcesUsername);
        textInputChangeCodewarsUsername = findViewById(R.id.textInputChangeCodewarsUsername);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeGroup.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveChangesMenuButton:
                onButtonSaveChangesClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_account_info_menu, menu);
        return true;
    }

    public void onButtonSaveChangesClick() {

        if (!validateFields()) return;

//        UserDto userDto = coreCommunicationService.sendLoginRequest(url, username, password);
//        if (userDto == null)
//            return;
//
//
//        Intent intent = new Intent(".EditAccountActivity");
//        startActivity(intent);
        finish();
    }

    public boolean validateFields() {

        boolean valid = true;

        if (textInputChangeUsername.getEditText().getText().length() == 0) newUsername = null;
        else {
            newUsername = textInputChangeUsername.getEditText().getText().toString().trim();
            //TODO: check for unique (request to core)
        }

        if (textInputChangeFullname.getEditText().getText().length() == 0) {
            newFullname = null;
            textInputChangeFullname.setError(null);
        } else {
            newFullname = textInputChangeFullname.getEditText().getText().toString().trim();
            if (!newFullname.matches("[a-zA-Z]+")) {
                valid = false;
                textInputChangeFullname.setError("Are you really? Your name contain only letters.");
            } else
                textInputChangeFullname.setError(null);

        }

        if (textInputChangeAge.getEditText().getText().length() == 0) newAge = null;
        else {
            newAge = Integer.parseInt(textInputChangeAge.getEditText().getText().toString().trim());
            if (newAge < 10 || newAge > 99) {
                valid = false;
                textInputChangeAge.setError("Don't lie me. From 10 to 99.");
            } else
                textInputChangeAge.setError(null);
        }

        return valid;
    }
}
