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
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;

    private Button back;

    public LiveRecorder liveRecorder;
    public Whisper whisper = new Whisper();
    private boolean isRecording;
    private String mealType;

    public RecordPage(int width, int height) {
        super(width, height);
    }

    public void setMealType(String mealType)
    {
        this.mealType = mealType;
    }
    public String getMealType()
    {
        return this.mealType;
    }
    public void addListeners() {
        // return to mainPage
        back.setOnAction(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
        });

        // initialize recording
        // open recipe
        recordButton.setOnAction(e -> {
            
            this.setIsrecording(!this.getIsRecording()); 

            if (this.getIsRecording()) {
                recordButton.setText("RECORDING...?");
                this.liveRecorder.startRecording();

            }
            if (!this.getIsRecording()) {
                this.liveRecorder.stopRecording();
                recordButton.setText("GOT VOICE");

                try {
                    // make recipe
                    TextToRecipe t2R = new TextToRecipe(this.whisper.getResponse(), mealType, new Recipe());
                    t2R.createRecipeObj();
                    SeeRecipeFromRecording SRP = new SeeRecipeFromRecording(constants.width, constants.height);
                    SRP.setRecipe(t2R.getRecipe());

                    // make a listener for recipeTitleView
                    RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
                    

                    recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                        StageController stg = StageController.getInstance();
                        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                        stg.changeTo(t2R.getRecipe().getRecipeTitle());
                    });

                    StageController stg = StageController.getInstance();
                    stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                    stg.changeTo(t2R.getRecipe().getRecipeTitle());


                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        back.setOnAction(e->{
            StageController stg = StageController.getInstance();
            stg.changeTo("MealTypePage");
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
            paneHeader.setTitleInMiddle(new Text("Say your "+mealType+" Ingredients..."));
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

            back = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +

                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            this.paneFooter.setButton(recordButton);
            paneFooter.setButton(back);
            isRecording = false;
            this.liveRecorder = new LiveRecorder();

            addListeners();
        }
    }


}
