package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class RecordPage extends Page {
    private paneHeader paneHeader;
    //    private Footer footer;
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    private Button backButton;
    public LiveRecorder liveRecorder;
    public Whisper whisper = new Whisper();
    private boolean isRecording;

    public RecordPage(int width, int height) {
        super(width, height);
    }

    public void addListeners() {
        backButton.setOnAction(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
            System.out.println("SWITCHED TO MAIN PAGE");
        });
        recordButton.setOnAction(e -> {
            // isRecording = !isRecording; // TOGGLES
            this.setIsrecording(!this.getIsRecording()); // this works?

            if (this.getIsRecording()) {
                recordButton.setText("RECORDING...?");
                this.liveRecorder.startRecording();

            }
            if (!this.getIsRecording()) {
                // HERE WE WOULD OPEN THE NEW WINDOW
                this.liveRecorder.stopRecording();
                recordButton.setText("GOT VOICE");

                try {
                    // make recipe
                    TextToRecipe t2R = new TextToRecipe(this.whisper.getResponse(), "lunch", new Recipe());
                    t2R.createRecipeObj();
                    SeeRecipeFromRecording SRP = new SeeRecipeFromRecording(600, 600);
                    SRP.setRecipe(t2R.getRecipe());

                    // make a listener for recipeTitleView
                    RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
                    //remove this ??
                    //RecipeTitleListView.getInstance().getChildren().add(recipeTitleView); 

                    recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                        StageController stg = StageController.getInstance();
                        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                        stg.changeTo(t2R.getRecipe().getRecipeTitle());
                    });

                    StageController stg = StageController.getInstance();
                    stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                    stg.changeTo(t2R.getRecipe().getRecipeTitle());

//                    Stage stage = (Stage) this.getScene().getWindow();
//                    stage.setScene(SRP.getScene());
                    // System.out.println(rp.whisper.getResponse());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private boolean getIsRecording() {
        return isRecording;
    }

    public void setIsrecording(boolean b) {
        this.isRecording = b;
    }

    @Override
    protected void createView() {
        {

            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Say your Ingredients..."));
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

            this.recordButton = paneFooter.creatButton("MICRPHONE", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.backButton = paneFooter.creatButton("BACK BUTTON", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.paneFooter.setButton(backButton);
            this.paneFooter.setButton(recordButton);
            isRecording = false;
            this.liveRecorder = new LiveRecorder();

            addListeners();
        }
    }
}
