package RooterPage;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    private ImageView image;
    private String name;
    private int  price;



    public Item(ImageView image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

   
    public ImageView getImage() {
        return image;
    }
    public void setImage(ImageView image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
