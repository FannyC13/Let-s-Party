package LocationPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LocationFilterController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String queryString;

    @FXML
    void Search() {
    };

    @FXML
    private ImageView Filter;

    @FXML
    private ImageView FilterBoxB;

    @FXML
    private ImageView Cart;

    @FXML
    void Cart(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/RooterPage/Cart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void FilterBoxB(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/LocationPage/Location2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void FilterC(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/RooterPage/Rooter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Debut fonctions filtres
    @FXML
    private TextField MaxPrice;

    @FXML
    private TextField MinPrice;

    @FXML
    private Button FilterButton;

    @FXML
    private ComboBox<String> SortBy;

    @FXML
    private ComboBox<String> SortType;

    @FXML
    void FilterButton(MouseEvent event) throws IOException {
        System.out.println("FilterButton");
        if (MaxPrice.getText().matches("[0-9]+") && MinPrice.getText().matches("[0-9]+")) {
            System.out.println("\nmax.price=" + MaxPrice.getText() +
                    "\nmin.price=" + MinPrice.getText() + "\n");
            queryString = "Where price_location < " + MaxPrice.getText()
                    + " and price_location > "
                    + MinPrice.getText();

        } else if (MaxPrice.getText().matches("[0-9]+") && !MinPrice.getText().matches("[0-9]+")) {
            queryString = "Where price_location < " + MaxPrice.getText();
        }

        else if (!MaxPrice.getText().matches("[0-9]+") && MinPrice.getText().matches("[0-9]+")) {
            queryString = "Where price_location > "
                    + MinPrice.getText();
        }

        else {
            queryString = "";
        }
        if (SortType.getSelectionModel().getSelectedItem() != "All") {
            if (queryString.matches(".*[Where]+.*"))
                queryString += " and Name_location like '%" + SortType.getSelectionModel().getSelectedItem() + "%' ";
            else {
                queryString += " Where Name_location like '%" + SortType.getSelectionModel().getSelectedItem() + "%' ";

            }
        }

        switch (SortBy.getSelectionModel().getSelectedItem()) {
            case "Price higher to lower":
                queryString += " order by price_location ASC ";
                break;
            case "Price lower to higher":
                queryString += " order by price_location DESC";
                break;
            case "Newest arrival":
                break;
            case "Oldest arrival":
                break;
            default:
                break;

        }

        root = FXMLLoader.load(getClass().getResource("/LocationPage/Location2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SortBy.setItems(FXCollections.observableArrayList("Price higher to lower",
                "Price lower to higher", "Newest arrival", "Oldest arrival"));

        SortType.setItems(FXCollections.observableArrayList("All", "Party",
                "University", "club", "Karaoke", "Disco"));

        SortBy.getSelectionModel().select(0);
        SortType.getSelectionModel().select(0);

    }

}
// -----------------------------------------------------------------------
