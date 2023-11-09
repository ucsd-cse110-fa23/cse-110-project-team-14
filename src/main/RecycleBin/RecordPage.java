package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecordPage extends BorderPane {
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    public LiveRecorder liveRecorder;
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
