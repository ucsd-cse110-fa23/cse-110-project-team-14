package com.pantrypal.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

// pre formatted Footer class
public class PaneFooter extends HBox {

    private Map<String, Button> ButtonList = new HashMap<String, Button>();

    public PaneFooter() {
        this.setPrefSize(1000, 60);
        this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 0 0 15 15; -fx-background-radius: 0 0 15 15;");
        this.setAlignment(Pos.CENTER);
        ButtonList.put("a", new Button("a"));
        // Add shadow for depth
        DropShadow ds = new DropShadow();
        ds.setOffsetY(-3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        this.setEffect(ds);
    }

    // appends Button to Footer
    // styling already specified
    public void setButton(Button bt, String btStyle) {
        ButtonList.put(bt.getText(), bt);
        ButtonList.get(bt.getText()).setStyle(btStyle);
        this.getChildren().add(ButtonList.get(bt.getText()));
        this.setAlignment(Pos.CENTER);
    }

    // appends Button to Footer
    // default styling
    public void setButton(Button bt) {
        ButtonList.put(bt.getText(), bt);
        this.getChildren().add(ButtonList.get(bt.getText()));
        this.setAlignment(Pos.CENTER);
    }
    public void setButton(String name) {

        this.getChildren().add(ButtonList.get(name));
        this.setAlignment(Pos.CENTER);
    }

    // gets Button 
    // returns null if no matching name
    public Button getButton(String name) {

         if(ButtonList.get(name)!=null)
         {
             return ButtonList.get(name);
         }
         else
         {
             System.out.println("Button not found");
             return  null;
         }
    }

    // creates Button and adds to list of buttons on footer.
    public Button creatButton(String name, String style) {
        Button button = new Button(name);
        // System.out.println("in PaneFooter.creatButton");
        // System.out.println(button.getText()+", ID:" + System.identityHashCode(button));
        button.setStyle(style);
        ButtonList.put(name, button);
        return button;
    }


}