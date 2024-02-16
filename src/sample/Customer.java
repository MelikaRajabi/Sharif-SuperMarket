package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class Customer {
    @FXML
    private Button exit;
    @FXML
    private Button logOut;
    @FXML
    private Button availableProducts;
    @FXML
    private Button unavailableProducts;

    public void exit(){
        System.exit(0);
    }

    public void logOut() throws IOException {

        MainCustomer m = new MainCustomer();
        m.changeScene("sample.fxml");
    }

    public void showUnAvailable() throws IOException {
       MainCustomer m = new MainCustomer();
       m.changeScene("showUnAvailableProducts.fxml");
    }

    public void showAvailable() throws IOException {
        MainCustomer m = new MainCustomer();
        m.changeScene("showAvailableProducts.fxml");
    }
}
