import java.sql.SQLException;
import java.util.Arrays;

import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class App extends Application {
    public static java.sql.Statement stmt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        Image logo = new Image("Icon.png");
        primaryStage.getIcons().add(logo);
        primaryStage.show();

    }

    public static void main(String[] args) {

        try {
            stmt = Connect.getConnect();
            Object[][] A = Functions.createTable("All", "Location");
            for (Object[] r : A) {
                System.out.println(Arrays.toString(r));
                System.out.println("hihi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);

    }

}
