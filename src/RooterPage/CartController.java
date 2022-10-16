package RooterPage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CartController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView Filter;

    @FXML
    private Button Pay;

    @FXML
    void FilterC(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/RooterPage/rooter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Pay(MouseEvent event) {

    }

}
