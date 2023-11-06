package src.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecipeDetail {
    public static void display()//show the recipe detail page
    {
        Stage addScreen = new Stage();
        addScreen.initModality(Modality.APPLICATION_MODAL);
        /* Sets the window to be application modal,
        which means that when this window is open,
        the user cannot interact with other windows
        of the same application until this window is closed. */

        addScreen.setTitle("RecipeTitle");//ToDo: need to be replaced with recipe Title
        BorderPane borderPane = new BorderPane();
// 设置BorderPane的样式
        borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 3; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");
        Scene scene = new Scene(borderPane, 500, 650);
        VBox layout= new VBox(10);
        layout.getChildren().setAll(new Label("label 1"), new TextArea("aaaaa"));
        layout.setAlignment(Pos.CENTER);
        borderPane.setCenter(layout);
        addScreen.setScene(scene);
        //Scene scene1= new Scene(layout, 300, 250);

        //addScreen.setScene(scene1);

        addScreen.showAndWait();
    }


}
