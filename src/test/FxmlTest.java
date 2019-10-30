package test;

import view.Dialog;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author GOLD
 */
public class FxmlTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Test for fxml document");
        Dialog dialog = new Dialog();
        dialog.show();
    }

}