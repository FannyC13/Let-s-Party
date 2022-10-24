package LocationPage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Location {
    private  SimpleStringProperty Address, Name,Description;
    private  ImageView image;
    private  SimpleDoubleProperty Price;
    private Button button;    

    public Location getLocation(Button button){
        return this;
    }

    Location() {
    
    }

    Location(String address, String Name, String Description,Double Price, ImageView image, Button button){
        this.Address = new SimpleStringProperty(address);
        this.Name = new SimpleStringProperty(Name);
        this.Description = new SimpleStringProperty(Description);
        this.image = image;
        this.Price = new SimpleDoubleProperty(Price);
        this.button = button;
    }
  
    public String getAddress() {
        return this.Address.get();
    }

    public String getName() {
        return this.Name.get();
    }

    public String getDescription() {
        return this.Description.get();
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Double getPrice() {
        return this.Price.get();
    }

    public Button getButton() {
        return button;
    }

    public void setDescription(String Description){
        this.Description.set(Description);
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public void setPrice(Double price) {
        this.Price.set(price);
    }

    public void setButton(Button button) {
        this.button = button;
    }

}
