package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class paneHeader extends HBox {

    private Text titleText;
    public paneHeader() {
        this.setPrefSize(1000, 60);
        this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 15 15 0 0; -fx-background-radius: 15 15 0 0;");
        // Add shadow for depth
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        this.setEffect(ds);
    }
    public void setTitleInMiddle(Text title)
    {
        this.titleText = title;
        titleText.setFill(Color.web("#8B4513"));  // Saddle Brown
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER);
    }
    public Text getTitleText()
    {
        return titleText;
    }
}