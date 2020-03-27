package com.example.arena;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;
import com.example.arena.validation.ValueValidator;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    private CoreCommunicationService coreCommunicationService;
    private ValueValidator valueValidator;

    private TextInputLayout textInputChangeEmail;
    private String email;
    private TextInputLayout textInputChangeUsername;
    private String newUsername;
    private TextInputLayout textInputChangeFullname;
    private String newFullname;
    private TextInputLayout textInputChangeAge;
    private Integer newAge;
    private Spinner spinnerChangeGroup;
    private String newGroup;
    private ArrayAdapter<CharSequence> spinnerAdapter;

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
        this.valueValidator = new ValueValidator(this);

        textInputChangeEmail = findViewById(R.id.textInputChangeEmail);
        textInputChangeUsername = findViewById(R.id.textInputChangeUsername);
        textInputChangeFullname = findViewById(R.id.textInputChangeFullname);
        textInputChangeAge = findViewById(R.id.textInputChangeAge);
        spinnerChangeGroup = findViewById(R.id.spinnerChangeGroup);

        textInputOldPassword = findViewById(R.id.textInputOldPassword);
        textInputNewPassword = findViewById(R.id.textInputNewPassword);

        textInputChangeCodeforcesUsername = findViewById(R.id.textInputChangeCodeforcesUsername);
        textInputChangeCodewarsUsername = findViewById(R.id.textInputChangeCodewarsUsername);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeGroup.setAdapter(spinnerAdapter);

        setInitialValues();
        setTitle(getString(R.string.edit_account_activity_title));
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
        System.out.println("Validation successfully passed!");
        updateUserSessionFields();
        coreCommunicationService.sendUpdateUserInfoRequest(UserSession.loggedUser);

        finish();
    }

    public void updateUserSessionFields() {

        if (!textInputChangeUsername.getEditText().getText().equals(UserSession.loggedUser.getUsername()))
            UserSession.loggedUser.setUsername(textInputChangeUsername.getEditText().getText().toString());

        if (!textInputChangeFullname.getEditText().getText().equals(UserSession.loggedUser.getFullName()))
            UserSession.loggedUser.setFullName(textInputChangeFullname.getEditText().getText().toString());

        if (!textInputChangeAge.getEditText().getText().equals(UserSession.loggedUser.getAge()))
            UserSession.loggedUser.setAge(Integer.parseInt(textInputChangeAge.getEditText().getText().toString()));

        if (!spinnerChangeGroup.getSelectedItem().toString().equals(UserSession.loggedUser.getGroup()))
            UserSession.loggedUser.setGroup(spinnerChangeGroup.getSelectedItem().toString());

        if (!textInputNewPassword.getEditText().getText().equals(UserSession.loggedUser.getPassword()))
            UserSession.loggedUser.setPassword(textInputNewPassword.getEditText().getText().toString());

        if (!textInputChangeCodeforcesUsername.getEditText().getText().equals(UserSession.loggedUser.getCodeForcesUsername()))
            UserSession.loggedUser.setCodeForcesUsername(textInputChangeCodeforcesUsername.getEditText().getText().toString());

        if (!textInputChangeCodewarsUsername.getEditText().getText().equals(UserSession.loggedUser.getCodeWarsUsername()))
            UserSession.loggedUser.setCodeWarsUsername(textInputChangeCodewarsUsername.getEditText().getText().toString());
    }

    public boolean validateFields() {

        boolean valid = true;

        //Username
        if (textInputChangeUsername.getEditText().getText().length() == 0) newUsername = null;
        else {
            newUsername = textInputChangeUsername.getEditText().getText().toString().trim();
            if (valueValidator.validateUsername(newUsername)) {
                textInputChangeUsername.setError(null);
            } else {
                valid = false;
                textInputChangeUsername.setError("This username already taken, sorry bro :(");
            }
        }

        //Fullname
        if (textInputChangeFullname.getEditText().getText().length() == 0) {
            newFullname = null;
            textInputChangeFullname.setError(null);
        } else {
            newFullname = textInputChangeFullname.getEditText().getText().toString().trim();
            if (valueValidator.validateFullname(newFullname)) {
                textInputChangeFullname.setError(null);
            } else {
                valid = false;
                textInputChangeFullname.setError("This is not your real name.");
            }
        }

        //Age
        if (textInputChangeAge.getEditText().getText().length() == 0) newAge = null;
        else {
            newAge = Integer.parseInt(textInputChangeAge.getEditText().getText().toString().trim());
            if (valueValidator.validateAge(newAge)) {
                textInputChangeAge.setError(null);
            } else {
                valid = false;
                textInputChangeAge.setError("Hmm..");
            }
        }

        //Old password
        Integer oldPasswordFieldLength = textInputOldPassword.getEditText().getText().length();
        Integer newPasswordFieldLength = textInputNewPassword.getEditText().getText().length();
        if (oldPasswordFieldLength == 0 && newPasswordFieldLength == 0) {
            textInputOldPassword.setError(null);
            textInputNewPassword.setError(null);
            newPassword = null;
        } else if (oldPasswordFieldLength == 0 && newPasswordFieldLength != 0) {
            textInputOldPassword.setError("Fill this field to change password");
            textInputNewPassword.setError(null);
            valid = false;
        } else if (oldPasswordFieldLength != 0 && newPasswordFieldLength == 0) {
            textInputOldPassword.setError(null);
            textInputNewPassword.setError("Fill this field to change password");
            valid = false;
        } else {
            String currentPassword = textInputOldPassword.getEditText().getText().toString();
            String newPassword = textInputOldPassword.getEditText().getText().toString();

            if (valueValidator.validateCurrentPassword(currentPassword))
                textInputOldPassword.setError(null);
            else {
                textInputOldPassword.setError("Do you forgot your password, man?");
                valid = false;
            }

            if (valueValidator.validateNewPassword(newPassword))
                textInputNewPassword.setError(null);
            else {
                textInputNewPassword.setError("Must be at least 8 chars long. Don't you know that?");
                valid = false;
            }
        }


        //Codeforces handle
        if (textInputChangeCodeforcesUsername.getEditText().getText().length() == 0)
            newCodeforcesUsername = null;
        else {
            newCodeforcesUsername = textInputChangeCodeforcesUsername.getEditText().getText().toString();
            System.out.println("+" + newCodeforcesUsername);
            if (valueValidator.validateCodeforcesUsername(newCodeforcesUsername)) {
                textInputChangeCodeforcesUsername.setError(null);
            } else {
                valid = false;
                textInputChangeCodeforcesUsername.setError("This codeforces handle not exist");
            }
        }

        return valid;
    }

    public void setInitialValues() {

        textInputChangeEmail.getEditText().setText(UserSession.loggedUser.getEmail());
        textInputChangeUsername.getEditText().setText(UserSession.loggedUser.getUsername());
        textInputChangeFullname.getEditText().setText(UserSession.loggedUser.getFullName());
        textInputChangeAge.getEditText().setText(UserSession.loggedUser.getAge().toString());
        int index = spinnerAdapter.getPosition(UserSession.loggedUser.getGroup());
        spinnerChangeGroup.setSelection(index);
        textInputChangeCodeforcesUsername.getEditText().setText(UserSession.loggedUser.getCodeForcesUsername());
        textInputChangeCodewarsUsername.getEditText().setText(UserSession.loggedUser.getCodeWarsUsername());
    }
}
