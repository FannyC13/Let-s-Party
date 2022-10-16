package App;
import java.sql.SQLException;
import java.util.Arrays;
import Functions.Functions;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        Image logo = new Image("Icon.png");
        primaryStage.getIcons().add(logo);
        primaryStage.show();
       
        
    }
    
    public static void main(String[] args) {
       /*  AppController a = new AppController();
        a.getConnection();
        try {
            Object[][] A = Functions.createTable("Name_Services,Description_Services,Price_Services,Image_Services", "services","WHERE Type = 'Person'");
            for(Object[] r: A){
                System.out.println(Arrays.toString(r));
                System.out.println("hihi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        launch(args);
        
    }

    
}
