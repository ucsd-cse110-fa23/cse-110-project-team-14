package com.pantrypal.view;

import com.pantrypal.controller.RegisterPageController;
import com.pantrypal.model.CreateAccount;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class RegisterPage extends Page {
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Button createAccount;
    private Button backToLogin;
    public TextField userName; 
    public PasswordField password;
    private PasswordField confirmPassword;
    private Label isPassWordsMatch;
    public Label userTakenLabel;
    public boolean match = false;
    public CreateAccount accountCreater = new CreateAccount();
    private RegisterPageController rpc; 

    public RegisterPage(int width, int height) {
        super(width, height);
    }

    @Override
    protected void createView() {
        {

            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Sign up!"));
            
            
            isPassWordsMatch = new Label();
            Text uText = new Text("User Name: ");
            Text pText = new Text("Password Name: ");
            Text p2Text = new Text("Confrim Password: ");

            userName = new TextField();
            password = new PasswordField();
            confirmPassword = new PasswordField();
            isPassWordsMatch = new Label();
            userTakenLabel = new Label();

            userName.setPromptText("Enter Username:");
            password.setPromptText("Enter Password:");
            confirmPassword.setPromptText("Re-Enter Password:");
             
            HBox userNameBox = new HBox();
            userNameBox.getChildren().addAll(uText, userName);
            userNameBox.setAlignment(Pos.CENTER);
            
            HBox passwordBox = new HBox();
            passwordBox.getChildren().addAll(pText, password);
              passwordBox.setAlignment(Pos.CENTER);
            
            HBox confirmPasswordBox = new HBox();
            confirmPasswordBox.getChildren().addAll(p2Text, confirmPassword);
            confirmPasswordBox.setAlignment(Pos.CENTER);
            
            
            mainContent.getChildren().add(userTakenLabel);
            mainContent.getChildren().add(userNameBox);
            mainContent.getChildren().add(passwordBox);
            mainContent.getChildren().add(confirmPasswordBox);
            mainContent.getChildren().add(isPassWordsMatch);

            confirmPassword.textProperty().addListener(e -> {
                // check if passwords match
                if(password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    // isPasswordMach set text
                    isPassWordsMatch.setText("You're good to go!");
                    match = true;
                    
                }
                else
                {
                    //passwords dont match
                    isPassWordsMatch.setText("Error: Passwords dont match");
                    match = false;
                }
            });
            
            isPassWordsMatch.setTextFill(Color.web("#8B4513"));
            isPassWordsMatch.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            userTakenLabel.setTextFill(Color.web("#8B4513"));
            userTakenLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            
            
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

            this.createAccount = paneFooter.creatButton("Create Account", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            this.backToLogin = paneFooter.creatButton("Back to Login", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            this.paneFooter.setButton(createAccount);
            this.paneFooter.setButton(backToLogin);
            rpc = new RegisterPageController(this);
            
        }
    }

    public void setBackLoginAction(EventHandler<ActionEvent> eventHandler){
        this.backToLogin.setOnAction(eventHandler);
    }

    public void setCreateAccountAction(EventHandler<ActionEvent> eventHandler){
        this.createAccount.setOnAction(eventHandler);
    }

    

     
}
