package RooterPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import App.*;
import Functions.Functions;
import LocationPage.LocationController;

public class CartController implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ImageView Filter;

    @FXML
    private Button Pay;

    @FXML
    private Button Reset;

    @FXML
    private Label Price;

    @FXML
    private TableColumn<Item, Image> clnImage;

    @FXML
    private TableColumn<Item, String> clnName;

    @FXML
    private TableColumn<Item, Integer> clnPrice;

    @FXML
    private TableView<Item> kart;

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

    public static void addPrice(int price){
        App.bill += price;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clnImage.setCellValueFactory(new PropertyValueFactory<>("Image"));

        clnName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        clnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        Price.setText(String.valueOf(App.bill));

        kart.setItems(App.KartRows);
    }

    @FXML
    void Reset(ActionEvent event) {
        LocationController.initialized = true;
        App.KartRows.clear();
        kart.setItems(App.KartRows);

    }

   /* 
    Button[] buttons = new Button[5];

   public void addButtons(ObservableList<Item> l){
        try{
            int i1 = 0;
            int i2 = 1;
            for(int i = 0; i < l.size(); i++){
                Button button = new Button("Add");
                Button button2 = new Button("Retrieve");

                buttons[i1] = button;
                buttons[i1].setId( String.valueOf(i1));

                buttons[i2] = button2;
                buttons[i2].setId( String.valueOf(i2));

                l.get(i).setAdd(button);
                l.get(i).setRemove(button2);

                i1 += 2;
                i2 += 2;
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    } */

   /* public void LocationTab2(ObservableList<Item> l) {

       try {

           Object[][] A = Functions.createTable("All", "Item", "");

           for (Object[] r : A) {            
               ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(String.valueOf(r[2]))));
               photo.setFitHeight(60);
               photo.setFitWidth(60);

               Button button = new Button("Add");
               Button button2 = new Button("Retrieve");

               buttons[i] = button;
               buttons[i].setId( String.valueOf(i));
               buttons[i].setOnAction(this::addToKart);



               Location loc = new Location(String.valueOf(r[0]), String.valueOf(r[3]), String.valueOf(r[1]),
                       Integer.parseInt(r[4].toString()), photo, buttons[i]);
               l.add(loc);
               LocationTable.setItems(l);
               i++;
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }

   } */
}
/*     @Override
    public void initialize(URL url, ResourceBundle rb) {

        LocationIm.setCellValueFactory(new PropertyValueFactory<>("Image"));

        AddressLoc.setCellValueFactory(new PropertyValueFactory<>("Address"));


        DescriptionLoc.setCellValueFactory(new PropertyValueFactory<>("Description"));

        NameLoc.setCellValueFactory(new PropertyValueFactory<>("Name"));

        LocationCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        addButton.setCellValueFactory(new PropertyValueFactory<>("button"));
    } */


