package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecordPage extends BorderPane {
//    private Header header;
    private paneHeader paneHeader;
//    private Footer footer;
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    public LiveRecorder liveRecorder;
    public Whisper whisper = new Whisper();
    private boolean isRecording;

    RecordPage() {
        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);

        this.paneHeader = new paneHeader();
        paneHeader.setTitleInMiddle(new Text("Say your Ingredients..."));
        this.center = mainContent;
        this.paneFooter = new paneFooter();

        this.setTop(this.paneHeader);
        this.setCenter(this.center);
        this.setBottom(this.paneFooter);

        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        this.recordButton = paneFooter.creatButton("MICRPHONE","-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.setButton(recordButton);
        isRecording = false;
        this.liveRecorder = new LiveRecorder();

        // addListeners();
    }

    // public void addListeners() {
    //     recordButton.setOnAction(e -> {
    //         isRecording = !isRecording; // TOGGLES
    //         if (isRecording) {
    //             liveRecorder.startRecording();
    //             recordButton.setText("RECORDING...?");
    //         }
    //         if (!isRecording) {
    //             // HERE WE WOULD OPEN THE NEW WINDOW
    //             recordButton.setText("GOT VOICE");
    //             liveRecorder.stopRecording();
    //             try {
    //                 // TextToRecipe t2R = new TextToRecipe(whisper.getResponse(), "lunch", new Recipe());
    //                 //TODO:

    //                 // t2R.createRecipeObj();
    //                 System.out.println(whisper.getResponse());
    //             }
    //             // catch (InterruptedException e1) {
    //             //     // TODO Auto-generated catch block
    //             //     e1.printStackTrace();
    //             // }
    //             catch (IOException e1) {
    //                 e1.printStackTrace();
    //             } catch (URISyntaxException e1) {
    //                 e1.printStackTrace();
    //             }
    //         }
    //     });
    // }

    public Button getRecordButton() {
        return this.recordButton;
    }
    //getter and setter for isRecording
    public boolean getIsRecording(){
        return isRecording;
    }
    
    public void setIsrecording(boolean b){
        this.isRecording = b;
    }




}
