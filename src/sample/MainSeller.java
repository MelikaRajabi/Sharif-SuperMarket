package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainSeller extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("seller.fxml"));
        primaryStage.setTitle("SharifMarket");
        primaryStage.setScene(new Scene(root, 1280,800 ));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
