package com.pantrypal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.FileWriter;

public class LoginPage extends Page {
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Button loginButton;
    private Button createAccount;
    private TextField userName; 
    private PasswordField password;
    private Label validLogin;
    boolean match = false;
    boolean autoLoginEnabled = false;
    private Login login;
    private CheckBox autologin;

    public LoginPage(int width, int height){
        super(width, height);
        this.login =  new Login();
    }
    
    @Override
    protected void createView() {
        {
            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Login!"));
            
            Text uText = new Text("User Name: ");
            Text pText = new Text("Password Name: ");

            userName = new TextField();
            password = new PasswordField();
            validLogin = new Label();
            autologin = new CheckBox("Remember Me");

            userName.setPromptText("Enter Username:");
            password.setPromptText("Enter Password:");
             
            HBox userNameBox = new HBox();
            userNameBox.getChildren().addAll(uText, userName);
            userNameBox.setAlignment(Pos.CENTER);
            
            HBox passwordBox = new HBox();
            passwordBox.getChildren().addAll(pText, password);
              passwordBox.setAlignment(Pos.CENTER);
            
            mainContent.getChildren().add(userNameBox);
            mainContent.getChildren().add(passwordBox);
            mainContent.getChildren().add(autologin);
          
            validLogin.setTextFill(Color.web("#8B4513"));
            validLogin.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            
            this.center = mainContent;
            this.paneFooter = new paneFooter();
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            this.borderPane.setBottom(this.paneFooter);

            this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                    "-fx-border-color: #DEB887; " +
                    "-fx-border-width: 10; " +
                    "-fx-border-radius: 15; " +
                    "-fx-background-radius: 15;");

            this.loginButton = new Button("Login");
            this.loginButton.setStyle("-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            this.createAccount = paneFooter.creatButton("Sign up!", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            // this.paneFooter.setButton(loginButton);
            mainContent.getChildren().add(loginButton);
            mainContent.getChildren().add(validLogin);
            this.paneFooter.setButton(createAccount);
            
            addListeners();
        }
    }
    
    private void addListeners(){
        this.loginButton.setOnAction(e -> {
            // check if credientals are valid
            match = login.checkCredentials(userName.getText().toString(), password.getText().toString());
            if(!match){
                //user didnt exist
                validLogin.setText("Failed to login! Check username and password.");
            }
            else{
                // change to main page
                StageController stg = StageController.getInstance();
                Globals.username = userName.getText().toString();
                stg.changeTo("mainPage"); //hasnt been made yet
                if(this.autologin.selectedProperty().getValue()){
                    login.setAutomaticLogin(userName.getText().toString(), password.getText().toString());
                }
            }
        });

        this.createAccount.setOnAction(e -> {
            StageController stg = StageController.getInstance();
            stg.changeTo("RegisterPage"); //hasnt been made yet
        });

        this.autologin.setOnAction(e1 -> {
            // autoLoginEnabled = !autoLoginEnabled;
            if(this.autologin.selectedProperty().getValue()){
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
        });
    }

    public void checkAutomaticLogin(){
        login.checkAutomaticLogin();
    }
}
