package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class showUnAvailableProducts {
    @FXML
    private Button showUnAvailableProducts;
    @FXML
    private Button menu;

    TableView<Good> unAvailableProducts = new TableView<>();

    public ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        for (int i = 0; i < Save.goods.size(); i++) {
            if (Save.goods.get(i).goodAmount.startsWith("0")) {
                goods.add(Save.goods.get(i));
            }
        }
        return goods;
    }

    public void setTable() {

        unAvailableProducts.getColumns().clear();

        TableColumn<Good, String> goodName = new TableColumn<>("NAME");
        goodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodName.setResizable(false);
        goodName.setMinWidth(400);
        TableColumn<Good, String> goodPrice = new TableColumn<>("PRICE");
        goodPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        goodPrice.setResizable(false);
        goodPrice.setMinWidth(400);

        unAvailableProducts.setItems(getGoods());
        unAvailableProducts.getColumns().addAll(goodName , goodPrice);
    }

    public void showUnAvailableProducts(){

        setTable();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(unAvailableProducts);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("unAvailableProducts");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void back() throws IOException {
        MainCustomer m = new MainCustomer();
        m.changeScene("customer.fxml");
    }
}
