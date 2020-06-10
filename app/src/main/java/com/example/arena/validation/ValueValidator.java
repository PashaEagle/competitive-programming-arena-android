package com.example.arena.validation;

import android.content.Context;

import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;

public class ValueValidator {

    private Context context;
    private CoreCommunicationService coreCommunicationService;

    public ValueValidator(Context context) {
        this.coreCommunicationService = new CoreCommunicationService(context);
    }

    public boolean validateUsername(String username) {
        if (UserSession.loggedUser.getUsername().equals(username)) return true;
        return !coreCommunicationService.checkUserExistsByUsernameRequest(username);
    }

    public boolean validateFullname(String fullname) {
        return fullname.matches("[a-zA-Z ]+");
    }

    public boolean validateAge(Integer age) {
        return age >= 10 && age <= 99;
    }

    public boolean validateCurrentPassword(String currentPassword) {
        return UserSession.loggedUser.getPassword().equals(currentPassword);
    }

    public boolean validateNewPassword(String newPassword) {
        return newPassword.length() >= 8;
    }

    public boolean validateCodeforcesUsername(String codeforcesUsername) {
        codeforcesUsername = codeforcesUsername.trim();
        if (codeforcesUsername.contains(" ")) return false;
        return coreCommunicationService.checkCodeforcesUsernameExists(codeforcesUsername);
    }

    public boolean validateCodewarsUsername(String codewarsUsername) {
        codewarsUsername = codewarsUsername.trim();
        if (codewarsUsername.contains(" ")) return false;
        return coreCommunicationService.checkCodewarsUsernameExists(codewarsUsername);
    }
}
