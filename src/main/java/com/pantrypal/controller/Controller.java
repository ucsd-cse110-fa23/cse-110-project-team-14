package com.pantrypal.controller;

import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public abstract class Controller {
    StageController stg;
    Controller()
    {
        stg = StageController.getInstance();
    }

}
