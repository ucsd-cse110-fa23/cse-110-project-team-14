package com.pantrypal.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.pantrypal.Globals;
import com.pantrypal.view.LoginPage;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;

public class LoginPageController {
    private LoginPage lp;
    public LoginPageController(LoginPage lp){
        this.lp = lp;
        this.lp.setCreateAccountAction(this::handleCreateAccount);
        this.lp.setLoginButtonAction(this::handleLogin);
        this.lp.setAutoLoginAction(this::handleAutoLogin);
    }

    public void handleCreateAccount(ActionEvent event){
        StageController stg = StageController.getInstance();
            stg.changeTo("RegisterPage"); 
    }

    public void handleLogin(ActionEvent event){
        // check if credientals are valid
            lp.match = lp.login.checkCredentials(lp.userName.getText().toString(), lp.password.getText().toString());
            if(!lp.match){
                //user didnt exist
                lp.validLoginLabel.setText("Failed to login! Check username and password.");
            }
            else{
                // change to main page
                StageController stg = StageController.getInstance();
                Globals.username = lp.userName.getText().toString();
                stg.changeTo("mainPage"); //hasnt been made yet
                if(this.lp.autologin.selectedProperty().getValue()){
                    lp.login.setAutomaticLogin(lp.userName.getText().toString(), lp.password.getText().toString());
                }
            }
    }

    public void handleAutoLogin(ActionEvent event){
        // autoLoginEnabled = !autoLoginEnabled;
            if(this.lp.autologin.selectedProperty().getValue()){
                // make file? 6
                try {
                File myObj = new File("autologin.txt");

                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");

                    // Clear the contents of the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(myObj))) {
                        // Write an empty string to clear the content
                        writer.write("");
                        System.out.println("File content cleared.");
                    } catch (IOException e) {
                        System.out.println("Error while clearing file content.");
                        e.printStackTrace();
                    }
                }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
    
    }

    
}
