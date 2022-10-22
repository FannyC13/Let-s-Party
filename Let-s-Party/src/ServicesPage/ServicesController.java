package ServicesPage;

import App.App;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Functions.Functions;
import RooterPage.CartController;
import RooterPage.Item;
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
    ObservableList<Services> l = FXCollections.observableArrayList();

    @FXML
    private Button Furniture;

    @FXML
    private Button Location;

    @FXML
    private HBox NavBar;

    @FXML
    private Button Services;

    @FXML
    private ImageView Filter;

    @FXML
    private TableColumn<Services, String> DescriptionServ;

    @FXML
    private TableColumn<Services, ImageView> ImageServ;

    @FXML
    private TableColumn<Services, String> NameServ;

    @FXML
    private TableColumn<Services, Double> PriceServ;
    
    @FXML
    private TableColumn<Services, Button> addButton;

    @FXML
    private TableView<Services> ServicesTable;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

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
        root = FXMLLoader.load(getClass().getResource("/ServicesPage/FilterServices.fxml"));
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
    void FilterC(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/RooterPage/Rooter.fxml"));
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

        DescriptionServ.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ImageServ.setCellValueFactory(new PropertyValueFactory<>("Image"));
        NameServ.setCellValueFactory(new PropertyValueFactory<>("Description"));
        PriceServ.setCellValueFactory(new PropertyValueFactory<>("Price"));
        addButton.setCellValueFactory(new PropertyValueFactory<>("button"));
        /*
         * ImageView photo = new ImageView(new
         * Image(this.getClass().getResourceAsStream("Villa.png")));
         * photo.setFitHeight(60);
         * photo.setFitWidth(60);
         * ImageView photo2 = new ImageView(new
         * Image(this.getClass().getResourceAsStream("DiscoBall.jpg")));
         * photo2.setFitHeight(60);
         * photo2.setFitWidth(60);
         * ObservableList<Services> l = FXCollections.observableArrayList();
         * Services Disco= new Services("Lacome", "Beautiful Pool Party", 200.0,
         * photo2);
         * Services LA = new Services("hihi", "Beautiful Party", 200.0, photo2);
         * l.add(Disco);
         * ServicesTable.setItems(l);
         */
        ServicesTab(l);

        // LocationT(LA,l);
        /* LocationT(Oval,l); */
    }

    Button[] buttons = new Button[5];
    int i = 0;
    public boolean check = false;
    public static ObservableList<Services> dataRows = FXCollections.observableArrayList();   

    public void ServicesTab(ObservableList<Services> l) {
        try {

            Object[][] A = Functions.createTable("Name_Services,Description_Services,Price_Services,Image_Services",
                    "services", "WHERE Type = 'Person'");
            for (Object[] r : A) {

                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(r[3].toString())));
                photo.setFitHeight(60);
                photo.setFitWidth(60);

                Button button = new Button("Add");

                buttons[i] = button;
                buttons[i].setId( String.valueOf(i));
                buttons[i].setOnAction(this::addToKart);

                Services serv = new Services(String.valueOf(r[0]), String.valueOf(r[1]),
                        Double.parseDouble(r[2].toString()), photo, buttons[i]);
                l.add(serv);
                ServicesTable.setItems(l);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToKart(ActionEvent event){
        int source = Integer.parseInt(((Button)event.getSource()).getId());

        if(App.initialized == false){
            for(Services bean : l){               
                dataRows.add(bean);   
            }

            System.out.println(dataRows.toString());
            Item first = new Item(dataRows.get(source).getImage(), dataRows.get(source).getName(),
                     dataRows.get(source).getPrice());

            App.KartRows.add(first);   
            CartController.addPrice(dataRows.get(source).getPrice());

            App.initialized = true;
        }
        else {
            int i1 = 0;
           
            while(i1 < dataRows.size()){
                if(dataRows.get(i1).getName().equals(dataRows.get(source).getName())){
                    JOptionPane.showMessageDialog(null, "Item already added to kart");
                    check = true;
                } 
                i1++;
            }

          /*   for(int i = 0; i < App.KartRows.size(); i++){
                if(App.KartRows.get(i).getName().equals(dataRows.get(source).getName())){
                    JOptionPane.showMessageDialog(null, "Item already added to kart");
                    check = true;
                } 
            } */

            if(check == false){
                for(Services bean : l){               
                    dataRows.add(bean);
                }
                Item first = new Item(dataRows.get(source).getImage(), dataRows.get(source).getName(),
                 dataRows.get(source).getPrice());
                 CartController.addPrice(dataRows.get(source).getPrice());

                App.KartRows.add(first);
            }

            check = false;
        }
    }

   
}
