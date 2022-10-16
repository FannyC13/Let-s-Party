package LocationPage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import Functions.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Furniture;

    @FXML
    private Button Location;

    @FXML
    private TableColumn<Location, String> AddressLoc;

    @FXML
    private TableColumn<Location, String> DescriptionLoc;

    @FXML
    private TableColumn<Location, Integer> LocationCol;

    @FXML
    private TableColumn<Location, ImageView> LocationIm;

    @FXML
    private TableView<Location> LocationTable;

    @FXML
    private TableColumn<Location, String> NameLoc;

    @FXML
    private HBox NavBar;

    @FXML
    private Button Services;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    void Furniture(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FurniturePage/Furniture.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Location(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/LocationPage/Location.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("/ServicesPage/Services.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Location> tab = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("here Controller");
        LocationIm.setCellValueFactory(new PropertyValueFactory<>("Image"));
        AddressLoc.setCellValueFactory(new PropertyValueFactory<>("Address"));
        DescriptionLoc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        NameLoc.setCellValueFactory(new PropertyValueFactory<>("Name"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
      
        ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream("Villa.png")));
        photo.setFitHeight(60);
        photo.setFitWidth(60);
        ImageView photo2 = new ImageView(new Image(this.getClass().getResourceAsStream("wedding.jpg")));
        photo2.setFitHeight(60);
        photo2.setFitWidth(60);
        Location Oval = new Location("Lacome", "Villa", "Beautiful Pool Party", 200, photo);
        Location LA = new Location("hihi", "Va", "Beautiful Party", 200, photo2);
        //tab.add(Oval);
        //LocationTable.setItems(tab);
        ObservableList<Location> l = FXCollections.observableArrayList();
        LocationTab2(l);
        /*LocationT(LA,l);
        LocationT(Oval,l);*/
    }
        
        //LocationTable.setEditable(true);
        //LocationTable.setItems(LocationTab(tab));
        

    /*public void LocationT(Location loc,  ObservableList<Location> l ){
       
        l.add(loc);
        LocationTable.setItems(l);
    }
    public ObservableList<Location> LocationTab(ObservableList<Location> tab){
        try {
           
            Object[][] A = Functions.createTable("All", "Location");
            for(Object[] r: A){
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(String.valueOf(r[4]))));
                photo.setFitHeight(60);
                photo.setFitWidth(60);
                Location loc = new Location(String.valueOf(r[0]), String.valueOf(r[1]), String.valueOf(r[2]), Integer.parseInt(r[3].toString()), photo);
                tab.add(loc);
                //LocationTable.setItems(tab);
            }
            return tab;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }*/

    public void LocationTab2( ObservableList<Location> l ){
        try {
           
            Object[][] A = Functions.createTable("All", "Location", "");
            for(Object[] r: A){
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(String.valueOf(r[4]))));
                photo.setFitHeight(60);
                photo.setFitWidth(60);
                Location loc = new Location(String.valueOf(r[0]), String.valueOf(r[1]), String.valueOf(r[2]), Integer.parseInt(r[3].toString()), photo);
                l.add(loc);
                LocationTable.setItems(l);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
}
