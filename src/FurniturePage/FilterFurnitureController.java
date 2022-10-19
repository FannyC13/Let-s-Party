package FurniturePage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import javafx.scene.Node;

public class FilterFurnitureController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
        root = FXMLLoader.load(getClass().getResource("/FurniturePage/Furniture2.fxml"));
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

    @FXML
    void Search(ActionEvent event) {

    }

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

    public static String queryString = "WHERE Type like 'Object%'";

    @FXML
    void FilterButton(MouseEvent event) throws IOException {
        queryString = "WHERE Type like 'Object%'";

        if (MaxPrice.getText().matches("[0-9]+") && MinPrice.getText().matches("[0-9]+")) {
            System.out.println("\nmax.price=" + MaxPrice.getText() +
                    "\nmin.price=" + MinPrice.getText() + "\n");
            queryString += " and price_services <= " + MaxPrice.getText()
                    + " and price_services >= "
                    + MinPrice.getText();

        } else if (MaxPrice.getText().matches("[0-9]+") && !MinPrice.getText().matches("[0-9]+")) {
            queryString += " and price_services <= " + MaxPrice.getText();
        }

        else if (!MaxPrice.getText().matches("[0-9]+") && MinPrice.getText().matches("[0-9]+")) {
            queryString += " and price_services >= "
                    + MinPrice.getText();
        }

        if (SortType.getSelectionModel().getSelectedItem() != "All") {
            if (queryString.matches(".*[Where]+.*"))
                queryString += " and Type like '%" + SortType.getSelectionModel().getSelectedItem() + "%' ";
            else {
                queryString += " and Type like '%" + SortType.getSelectionModel().getSelectedItem() + "%' ";

            }
        }
        System.out.println("\n premier " + queryString);

        switch (SortBy.getSelectionModel().getSelectedItem()) {
            case "Price higher to lower":
                queryString += " order by price_services ASC ";
                break;
            case "Price lower to higher":
                queryString += " order by price_services DESC";
                break;
            case "Newest arrival":
                break;
            case "Oldest arrival":
                break;
            default:
                break;

        }
        System.out.println("\ndeuxième" + queryString);

        root = FXMLLoader.load(getClass().getResource("/FurniturePage/Furniture2.fxml"));
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
