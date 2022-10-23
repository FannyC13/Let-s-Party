package RooterPage;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class RooterController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView Filter;

    @FXML
    private Button Furniture;

    @FXML
    private Button Location;

    @FXML
    private Button Services;

    @FXML
    void FilterB(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/LocationPage/Location2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Furniture(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FurniturePage/Furniture2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Location(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/LocationPage/Location2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Services(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ServicesPage/Services2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void FurnitureH(MouseEvent event) {
        Furniture.setOnMouseMoved(m -> {
            Furniture.setStyle("-fx-background-color: #c5c7c9;");
        });
        Furniture.setOnMouseExited(m -> {
            Furniture.setStyle("-fx-background-color:  #eee8cd;");
        });

    }

    @FXML
    void LocationH(MouseEvent event) {
        Location.setOnMouseMoved(m -> {
            Location.setStyle("-fx-background-color: #c5c7c9;");
        });
        Location.setOnMouseExited(m -> {
            Location.setStyle("-fx-background-color:  #eee8cd;");
        });

    }

    @FXML
    void ServicesH(MouseEvent event) {
        Services.setOnMouseMoved(m -> {
            Services.setStyle("-fx-background-color: #c5c7c9;");
        });
        Services.setOnMouseExited(m -> {
            Services.setStyle("-fx-background-color:  #eee8cd;");
        });
    }

}
