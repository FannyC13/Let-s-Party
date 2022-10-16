package LocationPage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Location {
    private  SimpleStringProperty Address, Name,Description;
    private  ImageView image;
    private  SimpleIntegerProperty Price;
    
    Location() {
    
    }

    Location(String address, String Name, String Description,int Price, ImageView image){
        this.Address = new SimpleStringProperty(address);
        this.Name = new SimpleStringProperty(Name);
        this.Description = new SimpleStringProperty(Description);
        this.image = image;
        this.Price = new SimpleIntegerProperty(Price);
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

    public int getPrice() {
        return this.Price.get();
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

    public void setPrice(int price) {
        this.Price.set(price);
    }

}
