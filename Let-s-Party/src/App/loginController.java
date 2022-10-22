package App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import Functions.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class loginController {

    @FXML
    private TextField Email;

    @FXML
    private Button Login;

    @FXML
    private Button Signin;

    @FXML
    private Label lblName;

    @FXML
    private SplitPane namepane;

    @FXML
    private TextField password;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane namePane;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void SignInOnClick(ActionEvent event) {
        namepane.setVisible(true);
        lblName.setVisible(true);
        txtName.setVisible(true);

        try {
            java.sql.Statement stat = Connect.getConnect();


            if (!Email.getText().isEmpty() && !password.getText().isEmpty() && !txtName.getText().isEmpty()) {

                String user = Email.getText();
                String pass = password.getText();

                String queryString = "Select email, Password from app_users where email='" + user + "' AND Password = '"
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

            } else{
                JOptionPane.showMessageDialog(null, "Please enter an Email, a password and a name");
                namePane.setVisible(true);
                }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    void onClick(ActionEvent event) {
        namePane.setVisible(false);
       

        try {


            java.sql.Statement stat = Connect.getConnect();

            if (!Email.getText().isEmpty() && !password.getText().isEmpty()) {

                String user = Email.getText();
                String pass = password.getText();

                String queryString = "Select email, Password, name from app_users where email='" + user
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
                    namePane.setVisible(true);
                    
                }

            } else
                JOptionPane.showMessageDialog(null, "Please enter an Email and a password");

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}