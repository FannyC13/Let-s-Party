package App;

import RooterPage.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class App extends Application {

    public static ObservableList<Item> KartRows;
    public static Double bill = 0.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Let's Party !");
        Image logo = new Image("LogoWhite.png");
        primaryStage.getIcons().add(logo);
        primaryStage.show();

    }

    public static void main(String[] args) {
        KartRows = FXCollections.observableArrayList();

        launch(args);

    }

}
