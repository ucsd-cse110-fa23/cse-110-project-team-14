package com.pantrypal;

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
    private TextField userName; 
    private PasswordField password;
    private PasswordField confirmPassword;
    private Label isPassWordsMatch;
    private Label userTaken;
    boolean match = false;
    private CreateAccount accountCreater = new CreateAccount();

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
            userTaken = new Label();

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
            
            
            mainContent.getChildren().add(userTaken);
            mainContent.getChildren().add(userNameBox);
            mainContent.getChildren().add(passwordBox);
            mainContent.getChildren().add(confirmPasswordBox);
            mainContent.getChildren().add(isPassWordsMatch);

            // TODO: logic for checking if passwords match 
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
            userTaken.setTextFill(Color.web("#8B4513"));
            userTaken.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            
            
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

            this.paneFooter.setButton(createAccount);
            
            addListeners();
        }
    }

    public void addListeners(){
        this.createAccount.setOnAction(e -> {
            if(match){
                // we create a new user
                boolean accMade = accountCreater.createAccount(userName.getText().toString(), password.getText().toString());
                if(!accMade){
                    //if user taken
                    userTaken.setText("Error: Account already existed with this username");
                }
                // redirect to login page? 
                else{
                    StageController stg = StageController.getInstance();
                    stg.changeTo("LoginPage"); //hasnt been made yet
                }
            }
        });
    }

    

     
}
