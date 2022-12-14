package Functions;

import java.beans.Customizer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;
/* 
import javax.swing.table.DefaultTableModel;
import javafx.scene.control.TableView;*/

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

public class Functions {

    public static Object[][] createTable(String columns, String Table, String Query) throws SQLException {
        if (!columns.matches("([A-Za-z]+[_]?[A-Za-z]*[,]?)+[A-Za-z]$")) {
            JOptionPane.showMessageDialog(null, "Enter Column name separated by comas or write All");
        } else
            try {

                Statement stmt = getConnect();
                String queryString;
                String[] col = columns.split(",");
                String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + Table + "'";
                System.out.println("query " + query);
                ResultSet re = stmt.executeQuery(query);
                int a = 0;
                while (re.next()) {
                    a++;
                }
                re = stmt.executeQuery(query);
                String[] name = new String[a];
                a = 0;
                while (re.next()) {
                    name[a] = re.getString(1);
                    a++;
                }
                if (columns.equals("All")) {
                    queryString = "select * from " + Table + " " + Query;
                    col = name;
                } else {
                    for (String c : col) {
                        if (Arrays.toString(name).contains(c) == false) {
                            JOptionPane.showMessageDialog(null, "Column " + c + " does not exist in the database");
                        }
                        ;
                    }
                    queryString = "select " + columns + " from " + Table + " " + Query;

                }
               
                ResultSet res = stmt.executeQuery(queryString);
                int i = 0;
                while (res.next()) {
                    i++;
                }
                ResultSet res2 = stmt.executeQuery(queryString);
                String[][] data = new String[i][col.length];
                i = 0;
                while (res2.next()) {
                    for (int j = 0; j < col.length; j++) {
                        String val = res2.getString(col[j]);
                        data[i][j] = val;
                    }
                    i++;
                }
               
                return data;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        return null;
    }

    public static java.sql.Statement getConnect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lets_party", "root",
                    "St??phane05");
            System.out.println("Database Connected");
            java.sql.Statement stmt = (java.sql.Statement) con.createStatement();
            return stmt;
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void setWrapCellFactory(TableColumn<Customizer, String> table) {
        table.setCellFactory(tablecol -> {
            TableCell<Customizer, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }
}

/*
 * public static Object[][] createTable2(String columns, String Table, String
 * Query) throws SQLException {
 * if (!columns.matches("([A-Za-z]+[_]?[A-Za-z]*[,]?)+[A-Za-z]$")) {
 * JOptionPane.showMessageDialog(null,
 * "Enter Column name separated by comas or write All");
 * } else
 * try {
 * Class.forName("com.mysql.cj.jdbc.Driver");
 * Connection con =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/lets_party", "root",
 * "St??phane05");
 * Statement stmt = con.createStatement();
 * String queryString;
 * String[] col = columns.split(",");
 * String query =
 * "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME IN '" +
 * Query + "'";
 * System.out.println("query" + query);
 * ResultSet re = stmt.executeQuery(query);
 * int a = 0;
 * while (re.next()) {
 * a++;
 * }
 * re = stmt.executeQuery(query);
 * String[] name = new String[a];
 * a = 0;
 * while (re.next()) {
 * name[a] = re.getString(1);
 * a++;
 * }
 * System.out.println("name[a] "+ Arrays.toString(name));
 * if (columns.equals("All")) {
 * col = name;
 * } else {
 * for (String c : col) {
 * if (Arrays.toString(name).contains(c) == false) {
 * JOptionPane.showMessageDialog(null, "Column " + c +
 * " does not exist in the database");
 * }
 * ;
 * }
 * queryString = "select " + columns + " from " + Table + " " + Query;
 * }
 * ResultSet res = stmt.executeQuery(queryString);
 * int i = 0;
 * while (res.next()) {
 * i++;
 * }
 * ResultSet res2 = stmt.executeQuery(queryString);
 * String[][] data = new String[i][col.length];
 * i = 0;
 * while (res2.next()) {
 * for (int j = 0; j < col.length; j++) {
 * String val = res2.getString(col[j]);
 * data[i][j] = val;
 * }
 * i++;
 * }
 * return data;
 * } catch (SQLException ex) {
 * ex.printStackTrace();
 * } catch (Exception ex) {
 * System.out.println(ex.toString());
 * }
 * return null;
 * }
 * }
 */

/*
 * StudentTable.setModel(new DefaultTableModel(
 * data,
 * col));
 * } catch (SQLException ex) {
 * ex.printStackTrace();
 * } catch (Exception ex) {
 * System.out.println(ex.toString());
 * }
 */