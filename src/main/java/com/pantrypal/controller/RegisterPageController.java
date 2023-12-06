package com.pantrypal.controller;

import com.pantrypal.Globals;
import com.pantrypal.view.EditRecipePage;
import com.pantrypal.view.RegisterPage;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;

public class RegisterPageController {
    private RegisterPage rp;
    public RegisterPageController(RegisterPage rp){
        this.rp = rp;
        this.rp.setCreateAccountAction(this::handleCreate);
        this.rp.setBackLoginAction(this::handleBack);
    }

    public void handleCreate(ActionEvent event){
         if(rp.match){
                // we create a new user
                boolean accMade = rp.accountCreater.createAccount(rp.userName.getText().toString(), rp.password.getText().toString());
                if(!accMade){
                    //if user taken
                    rp.userTakenLabel.setText("Error: Account already existed with this username");
                }
                // redirect to login page? 
                else{
                    StageController stg = StageController.getInstance();
                    stg.changeTo("LoginPage");
                }
            }
    }
    //controls back button action: switches page to loginPage
    public void handleBack(ActionEvent event){
        StageController stg = StageController.getInstance();
        stg.changeTo("LoginPage");
    }
    

    
}
