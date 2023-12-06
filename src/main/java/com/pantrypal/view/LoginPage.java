package com.pantrypal.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import com.pantrypal.Globals;
import com.pantrypal.controller.LoginPageController;
import com.pantrypal.model.Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public TextField userName; 
    public PasswordField password;
    public Label validLoginLabel;
    public boolean match = false;
    public boolean autoLoginEnabled = false;
    public Login login;
    public CheckBox autologin;
    private LoginPageController lpc;

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
            validLoginLabel = new Label();
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
          
            validLoginLabel.setTextFill(Color.web("#8B4513"));
            validLoginLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            
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
            mainContent.getChildren().add(validLoginLabel);
            this.paneFooter.setButton(createAccount);
            
            // addListeners();
            lpc = new LoginPageController(this);
        }
    }
    
    public void setLoginButtonAction(EventHandler<ActionEvent> eventHandler){
        this.loginButton.setOnAction(eventHandler);
    }

    public void setCreateAccountAction(EventHandler<ActionEvent> eventHandler){
        this.createAccount.setOnAction(eventHandler);
    }

    public void setAutoLoginAction(EventHandler<ActionEvent> eventHandler){
        this.autologin.setOnAction(eventHandler);
    }

    public void checkAutomaticLogin(){
        login.checkAutomaticLogin();
    }
}
