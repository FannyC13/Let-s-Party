package App;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Functions.Functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class loginController {

    @FXML
    private TextField Email;

    @FXML
    private Button Login;

    @FXML
    private Button Signin;

    @FXML
    private ImageView lblName;

    @FXML
    private TextField password;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane namepane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void SignInOnClick(ActionEvent event) {
        namepane.setVisible(true);
        lblName.setVisible(true);
        txtName.setVisible(true);

        try {
           
            java.sql.Statement stat = Functions.getConnect();

            if (!Email.getText().isEmpty() && !password.getText().isEmpty() && !txtName.getText().isEmpty()) {

                String user = Email.getText();
                String pass = password.getText();

                String queryString = "Select email, Password from app_user where email='" + user + "' AND Password = '"
                        + pass + "'";

                ResultSet res = stat.executeQuery(queryString);

                if (res.next()) {
                    JOptionPane.showMessageDialog(null, "This Account already exists");
                } else {
                    String Name = Email.getText();
                    queryString = "Insert into users values('" + user + "', '" + pass + "', '" + Name + "')";
                    stat.execute(queryString);

                    JOptionPane.showMessageDialog(null, "User " + Name + " added");

                }

            } else
                JOptionPane.showMessageDialog(null, "Please enter an Email, a password and a name");

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    void onClick(ActionEvent event) {
        namepane.setVisible(false);
        lblName.setVisible(false);
        ;
        txtName.setVisible(false);

        try {

        
            java.sql.Statement stat = Functions.getConnect();

            if (!Email.getText().isEmpty() && !password.getText().isEmpty()) {

                String user = Email.getText();
                String pass = password.getText();

                String queryString = "Select email, Password, name from app_user where email='" + user
                        + "' AND Password = '" + pass + "'";

                ResultSet res = stat.executeQuery(queryString);

                if (res.next()) {
                    JOptionPane.showMessageDialog(null, "welcome " + res.getString(3));
                    root = FXMLLoader.load(getClass().getResource("/LocationPage/Location2.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "This account doesn't exist, please sign in");
                    namepane.setVisible(true);
                    lblName.setVisible(true);
                    txtName.setVisible(true);
                }

            } else
                JOptionPane.showMessageDialog(null, "Please enter an Email and a password");

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'App.fxml'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'App.fxml'.";
        assert Signin != null : "fx:id=\"Signin\" was not injected: check your FXML file 'App.fxml'.";
        assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'App.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'App.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'App.fxml'.";

    }

}
