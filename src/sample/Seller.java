package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class Seller {

    @FXML
    private Button exit;
    @FXML
    private Button products;
    @FXML
    private Button availableProducts;
    @FXML
    private Button unAvailableProducts;
    @FXML
    private Button orders;
    @FXML
    private Button statistics;

    public void exit(){
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    public void showProducts() throws IOException {
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainSeller m = new MainSeller();
        m.changeScene("showProducts.fxml");
    }

    public void showAvailable() throws IOException {
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainSeller m = new MainSeller();
        m.changeScene("showAvailableProductss.fxml");
    }

    public void showUnAvailable() throws IOException {
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainSeller m = new MainSeller();
        m.changeScene("showUnAvailableProductss.fxml");
    }

    public void showOrders() throws IOException {
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainSeller m = new MainSeller();
        m.changeScene("showOrders.fxml");
    }

    public void showStatistics() throws IOException {
        try {
            Save.writeListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainSeller m = new MainSeller();
        m.changeScene("showStatistics.fxml");
    }



}
