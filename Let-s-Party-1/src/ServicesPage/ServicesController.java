package ServicesPage;

import App.App;
import RooterPage.*;

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
    private TableColumn<Customizer, String> DescriptionServ;

    @FXML
    private TableColumn<Services, ImageView> ImageServ;

    @FXML
    private TableColumn<Customizer, String> NameServ;

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
    private TextField Search;

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
       /* ServicesTable.getItems().clear();
        System.out.println(Search.getText());
        ObservableList<Services> l = FXCollections.observableArrayList();
        ServicesTab(l, "where Name_Services like '%" + Search.getText() + "%'");
        Search.setText("");
        if (ServicesTable.getItems().size() == 0) {
            JOptionPane.showMessageDialog(null, "The element does not exists");
            ServicesTab(l, "WHERE Type = 'Person'");
        }*/ 

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
        NameServ.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PriceServ.setCellValueFactory(new PropertyValueFactory<>("Price"));
        addButton.setCellValueFactory(new PropertyValueFactory<>("button"));


        Functions.setWrapCellFactory(DescriptionServ);
        Functions.setWrapCellFactory(NameServ);

        if (ServicesFilterController.queryString != null) {
            ServicesTab(l, ServicesFilterController.queryString);

        } else {
            ServicesTab(l, "");
        }

        FilteredList<Services> FiltredData = new FilteredList<>(l, b -> true);
        Search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            FiltredData.setPredicate(Services -> {
                if (newvalue.isEmpty() || newvalue.isBlank() || newvalue == null) {
                    return true;
                }
                String searchKeyword = newvalue.toLowerCase();

                if (Services.getDescription().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                }
                if (Services.getName().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<Services> sortedData = new SortedList<>(FiltredData);
        sortedData.comparatorProperty().bind(ServicesTable.comparatorProperty());
        ServicesTable.setItems(sortedData);
    }

    Button[] buttons = new Button[5];
    int i = 0;
    public boolean check = false;
    public boolean initialized=false;
    public static ObservableList<Services> dataRows = FXCollections.observableArrayList();   

    public void ServicesTab(ObservableList<Services> l, String query) {
        try {

            Object[][] A = Functions.createTable("Name_Services,Description_Services,Price_Services,Image_Services",
                    "services", query);
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

        for(Services bean : l){               
            dataRows.add(bean);   
        }

        if(initialized == false){
            System.out.println(dataRows.toString());
            Item first = new Item(dataRows.get(source).getImage(), dataRows.get(source).getName(),
                     dataRows.get(source).getPrice());

            App.KartRows.add(first);   
            CartController.addPrice(dataRows.get(source).getPrice());

            initialized = true;
        }
        else {
            int i1 = 0;
           
            while (i1 < App.KartRows.size()) {
                if (App.KartRows.get(i1).getName().equals(dataRows.get(source).getName())) {

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
