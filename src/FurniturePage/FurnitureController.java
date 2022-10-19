package FurniturePage;

import Functions.Functions;

import java.beans.Customizer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
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

public class FurnitureController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Furniture;

    @FXML
    private TableColumn<Furniture, ImageView> FurnitureIm;

    @FXML
    private TableView<Furniture> FurnitureTable;

    @FXML
    private TableColumn<Customizer, String> NameCol;

    @FXML
    private TableColumn<Customizer, String> DescriptionCol;

    @FXML
    private ImageView Filter;

    @FXML
    private TableColumn<Furniture, Double> PriceCol;
    @FXML
    private Button Location;

    @FXML
    private HBox NavBar;

    @FXML
    private Button Services;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private ImageView FilterBox;

    @FXML
    private ImageView Cart;

    @FXML
    private TextField Search;

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
       
        root = FXMLLoader.load(getClass().getResource("/FurniturePage/FilterFurniture.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Furniture(MouseEvent event) throws IOException {
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
        FurnitureTable.getItems().clear();
        System.out.println(Search.getText());
        ObservableList<Furniture> l = FXCollections.observableArrayList();
        FurnitureTab(l, "where Name_Services like '%" + Search.getText() + "%'");
        Search.setText("");
        System.out.println(FurnitureTable.getItems().size());
        if (FurnitureTable.getItems().size() == 0) {
            JOptionPane.showMessageDialog(null, "The element does not exists");
            FurnitureTab(l, "WHERE Type like 'Object%'");
        }
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
    void FilterC(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/RooterPage/Rooter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("here");
        FurnitureIm.setCellValueFactory(new PropertyValueFactory<>("Image"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Functions.setWrapCellFactory(DescriptionCol);
        Functions.setWrapCellFactory(NameCol);

        ObservableList<Furniture> l = FXCollections.observableArrayList();

        if (FilterFurnitureController.queryString != null) {
            FurnitureTab(l, FilterFurnitureController.queryString);

        } else {
            FurnitureTab(l, "WHERE Type like 'Object%'");
        }
        FilteredList<Furniture> FiltredData = new FilteredList<>(l, b -> true);
        Search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            FiltredData.setPredicate(Furniture -> {
                if (newvalue.isEmpty() || newvalue.isBlank() || newvalue == null) {
                    return true;
                }
                String searchKeyword = newvalue.toLowerCase();

                if (Furniture.getDescription().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                }
                if (Furniture.getName().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<Furniture> sortedData = new SortedList<>(FiltredData);
        sortedData.comparatorProperty().bind(FurnitureTable.comparatorProperty());
        FurnitureTable.setItems(sortedData);
    }

    public void FurnitureTab(ObservableList<Furniture> l, String query) {
        try {

            Object[][] A = Functions.createTable("Name_Services,Description_Services,Price_Services,Image_Services",
                    "services", query);
            for (Object[] r : A) {
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(String.valueOf(r[3]))));
                photo.setFitHeight(60);
                photo.setFitWidth(60);
                System.out.println("r" + Arrays.toString(r));
                Furniture fur = new Furniture(String.valueOf(r[0]), String.valueOf(r[1]),
                        Double.parseDouble(r[2].toString()), photo);
                l.add(fur);
                FurnitureTable.setItems(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
