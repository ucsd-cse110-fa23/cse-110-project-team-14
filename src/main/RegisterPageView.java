package src.main;

import javafx.scene.control.PasswordField;

public class RegisterPageView extends Page{
    private paneHeader paneHeader;
    // private paneFooter paneFooter;
    private VBox center;
    private Button createAccount;
    private TextField userName;
    private PasswordField passwordField; 

    public RegisterPageView(int width, int height) {
        super(width, height);
    }

    @Override
    protected void createView() {
        {

            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.center = mainContent;
            
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Sign Up!"));

            // this.paneFooter = new paneFooter();
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            // this.borderPane.setBottom(this.paneFooter);

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

            // this.paneFooter.setButton(recordButton);
            // paneFooter.setButton(back);
            isRecording = false;
            this.liveRecorder = new LiveRecorder();

            addListeners();
        }
    }

}
