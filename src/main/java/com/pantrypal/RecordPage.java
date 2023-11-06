package com.pantrypal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RecordPage extends BorderPane{
    private Header header;
    private Footer footer;
    private VBox center;
    private Button recordButton;
    private LiveRecorder liveRecorder;
    private Whisper whisper = new Whisper();
    private boolean isRecording; 

    RecordPage(){
        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);

        this.header = new Header();
        this.center = mainContent;
        this.footer = new Footer();

        this.setTop(this.header);
        this.setCenter(this.center);
        this.setBottom(this.footer);

        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                      "-fx-border-color: #DEB887; " +
                      "-fx-border-width: 10; " +
                      "-fx-border-radius: 15; " +
                      "-fx-background-radius: 15;");


        this.recordButton = footer.getRecordButton();
        isRecording = false;
        this.liveRecorder = new LiveRecorder();

        addListeners();
    }

    public void addListeners() {
        recordButton.setOnAction(e -> {
            isRecording = !isRecording; // TOGGLES
            if(isRecording){
                liveRecorder.startRecording();
                recordButton.setText("RECORDING...?");
            }
            if(!isRecording){
                // HERE WE WOULD OPEN THE NEW WINDOW
                recordButton.setText("GOT VOICE");
                liveRecorder.stopRecording();
                try {
                    // TextToRecipe t2R = new TextToRecipe(whisper.getResponse(), "lunch", new Recipe());
                   
                    // t2R.createRecipeObj();
                    System.out.println(whisper.getResponse());
                }
                // catch (InterruptedException e1) {
                //     // TODO Auto-generated catch block
                //     e1.printStackTrace();
                // }
                catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public Button getRecordButton(){
        return this.recordButton;
    }

    class Header extends HBox {
        Header() {
            this.setPrefSize(1000, 60);
            this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 15 15 0 0; -fx-background-radius: 15 15 0 0;");
            
            Text titleText = new Text("Say your Ingredients...");
            titleText.setFill(Color.web("#8B4513"));  // Saddle Brown
            titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
            this.getChildren().add(titleText);
            this.setAlignment(Pos.CENTER);
            
            // Add shadow for depth
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0);
            ds.setColor(Color.color(0.4, 0.4, 0.4));
            this.setEffect(ds);
        }
    }


class Footer extends HBox {
    private Button recordButton;

    Footer() {
        this.setPrefSize(1000, 60);
        this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 0 0 15 15; -fx-background-radius: 0 0 15 15;");

        recordButton = new Button("MICRPHONE");
        recordButton.setStyle("-fx-background-color: #FFEBD7; -fx-text-fill: #8B4513; -fx-border-color: #8B4513; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5 15 5 15;");

        this.getChildren().addAll(recordButton);
        this.setAlignment(Pos.CENTER);

        // Add shadow for depth
        DropShadow ds = new DropShadow();
        ds.setOffsetY(-3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        this.setEffect(ds);
    }

    public Button getRecordButton() {
        return recordButton;
    }
}

    
}
