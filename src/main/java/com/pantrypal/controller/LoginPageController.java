package com.pantrypal.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.pantrypal.Globals;
import com.pantrypal.view.LoginPage;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;

/**
 * Handles interactions with the LoginPage
 */
public class LoginPageController {
    private LoginPage lp;

    public LoginPageController(LoginPage lp) {
        this.lp = lp;
        this.lp.setCreateAccountAction(this::handleCreateAccount);
        this.lp.setLoginButtonAction(this::handleLogin);
        // this.lp.setAutoLoginAction(this::handleAutoLogin);
    }

    /**
     * Create the account and change to the register page
     */
    public void handleCreateAccount(ActionEvent event) {
        StageController stg = StageController.getInstance();
        stg.changeTo("RegisterPage");
    }

    public void handleLogin(ActionEvent event) {
        // check if credientals are valid
        lp.match = lp.login.checkCredentials(lp.userName.getText().toString(), lp.password.getText().toString());
        if (!lp.match) {
            // user didnt exist
            lp.validLoginLabel.setText("Failed to login! Check username and password.");
        } else {
            // change to main page
            StageController stg = StageController.getInstance();
            Globals.username = lp.userName.getText().toString();
            stg.changeTo("mainPage"); // hasnt been made yet
            if (this.lp.autologin.selectedProperty().getValue()) {
                lp.login.setAutomaticLogin(lp.userName.getText().toString(), lp.password.getText().toString());
            }
        }
    }
}
