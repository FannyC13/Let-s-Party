package LocationPage;

import java.beans.Customizer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Functions.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LocationController implements Initializable {
    private static final Button ObservableList = null;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Furniture;

    @FXML
    private Button Location;

    @FXML
    private TextField Search;

    @FXML
    private TableColumn<Customizer, String> AddressLoc;

    @FXML
    private TableColumn<Customizer, String> DescriptionLoc;

    @FXML
    private TableColumn<Location, Integer> LocationCol;

    @FXML
    private TableColumn<Location, ImageView> LocationIm;

    @FXML
    private TableView<Location> LocationTable;

    @FXML
    private TableColumn<Customizer, String> NameLoc;

    @FXML
    private HBox NavBar;

    @FXML
    private Button Services;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private ImageView Filter;

    @FXML
    private ImageView FilterBox;

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
    void FilterBox(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/LocationPage/FilterLocation.fxml"));
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
    void Search(ActionEvent event) {
        LocationTable.getItems().clear();
        System.out.println(Search.getText());
        ObservableList<Location> l = FXCollections.observableArrayList();
        LocationTab2(l, "where Name_location like '%" + Search.getText() + "%'");
        Search.setText("");
        System.out.println(LocationTable.getItems().size());
        if (LocationTable.getItems().size() == 0) {
            JOptionPane.showMessageDialog(null, "The element does not exists");
            LocationTab2(l, "");
        }
    }

    @FXML
    public void FilterC(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/RooterPage/Rooter.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LocationIm.setCellValueFactory(new PropertyValueFactory<>("Image"));
        AddressLoc.setCellValueFactory(new PropertyValueFactory<>("Address"));
        DescriptionLoc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        NameLoc.setCellValueFactory(new PropertyValueFactory<>("Name"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Functions.setWrapCellFactory(DescriptionLoc);
        Functions.setWrapCellFactory(AddressLoc);
        Functions.setWrapCellFactory(NameLoc);
        ObservableList<Location> l = FXCollections.observableArrayList();
        if (LocationFilterController.queryString != null) {
            LocationTab2(l, LocationFilterController.queryString);
        } else {
            LocationTab2(l, "");

        }

        FilteredList<Location> FiltredData = new FilteredList<>(l, b -> true);
        Search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            FiltredData.setPredicate(Location -> {
                if (newvalue.isEmpty() || newvalue.isBlank() || newvalue == null) {
                    return true;
                }
                String searchKeyword = newvalue.toLowerCase();

                if (Location.getAddress().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                }
                if (Location.getDescription().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                }
                if (Location.getName().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<Location> sortedData = new SortedList<>(FiltredData);
        sortedData.comparatorProperty().bind(LocationTable.comparatorProperty());
        LocationTable.setItems(sortedData);
    }

    public void LocationTab2(ObservableList<Location> l, String query) {
        try {

            Object[][] A = Functions.createTable("All", "Location", query);
            for (Object[] r : A) {

                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(String.valueOf(r[4]))));
                photo.setFitHeight(60);
                photo.setFitWidth(60);
                Location loc = new Location(String.valueOf(r[0]), String.valueOf(r[1]), String.valueOf(r[2]),
                        Integer.parseInt(r[3].toString()), photo);
                l.add(loc);

                LocationTable.setItems(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
