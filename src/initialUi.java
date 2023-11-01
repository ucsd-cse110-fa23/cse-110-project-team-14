package src;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.border.Border;

class Header extends HBox
{
    Header()
    {
        this.setPrefSize(1000, 60); // Size of the header
        this.setStyle("-fx-background-color: #F9E69A;");

        Text titleText = new Text("PantryPal----The best Recipe manager"); // Write what ever you want
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER);
    }
}
class Footer extends HBox//TODO: place for Button
{
    //TODO: create private Button variable here:
    Footer()
    {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F9E69A;");
        this.setSpacing(15);
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        //TODO: add Button here:


        //this.getChildren().addAll(); //TODO: adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center
    }
    //TODO: getButton methods:
}
class Appframe extends BorderPane
{

    //TODO: add the class we need, like button, Header, Footer

    ////Header:
    private Header header;
    ////Footer---for buttons
    private Footer footer;
    //TODO: add the RecipeList here:

    //TODO: add Button here:


    Appframe()
    {
        // Initialise the header Object
        header = new Header();

        //TODO: Create a RecipeList Object to hold the tasks


        // Initialise the Footer Object
        footer = new Footer();

        // Add header to the top of the BorderPane
        this.setTop(header);
        //TODO: Add scroller to the centre of the BorderPane

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        this.setStyle("-fx-background-color: #F9E69A;");//change to what ever color you like
        //TODO:Initialise Button Variables through the getters in Footer

        //TODO: add listener


    }
}









public class initialUi extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the RecipeList
        Appframe root = new Appframe();

        // Set the title of the app
        primaryStage.setTitle("PantryPal");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 600, 900));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}