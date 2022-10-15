import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServicesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button Furniture;

    @FXML
    private Button Location;

    @FXML
    private HBox NavBar;

    @FXML
    private TableColumn<?, ?> ServiceCol;

    @FXML
    private TableColumn<?, ?> ServiceIm;

    @FXML
    private Button Services;

    @FXML
    private TableView<?> ServicesTable;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    public void Furniture(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Furniture.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Location(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Location.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    public void Services(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Services.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
