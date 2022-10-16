import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServicesController implements Initializable {

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
    private Button Services;

    @FXML
    private TableColumn<Services,String> DescriptionServ;

    @FXML
    private TableColumn<Services,ImageView> ImageServ;

    @FXML
    private TableColumn<Services, String> NameServ;

    @FXML
    private TableColumn<Services, Double> PriceServ;

    @FXML
    private TableView<Services> ServicesTable;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("here Services");
        
        DescriptionServ.setCellValueFactory(new PropertyValueFactory<>("Description"));

        ImageServ.setCellValueFactory(new PropertyValueFactory<>("Image"));
        NameServ.setCellValueFactory(new PropertyValueFactory<>("Description"));
        PriceServ.setCellValueFactory(new PropertyValueFactory<>("Price"));
         /*
        ImageView photo = new ImageView(new
        Image(this.getClass().getResourceAsStream("Villa.png")));
        photo.setFitHeight(60);
        photo.setFitWidth(60);
        ImageView photo2 = new ImageView(new Image(this.getClass().getResourceAsStream("DiscoBall.jpg")));
        photo2.setFitHeight(60);
        photo2.setFitWidth(60);
        ObservableList<Services> l = FXCollections.observableArrayList();
        Services Disco= new Services("Lacome", "Beautiful Pool Party", 200.0,
        photo2);
        Services LA = new Services("hihi", "Beautiful Party", 200.0, photo2);
        l.add(Disco);
        ServicesTable.setItems(l);*/
        ObservableList<Services> l = FXCollections.observableArrayList();
        ServicesTab(l);
    
        // LocationT(LA,l);
        /* LocationT(Oval,l); */
    }

    public void ServicesTab(ObservableList<Services> l) {
        try {

            Object[][] A = Functions.createTable("Name_Services,Description_Services,Price_Services,Image_Services","services", "WHERE Type = 'Person'");
            for (Object[] r : A) {
                System.out.println("r" + Arrays.toString(r));
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(r[3].toString())));
                photo.setFitHeight(60);
                photo.setFitWidth(60);
                Services serv = new Services(String.valueOf(r[0]), String.valueOf(r[1]),Double.parseDouble(r[2].toString()), photo);
                l.add(serv);
                ServicesTable.setItems(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
