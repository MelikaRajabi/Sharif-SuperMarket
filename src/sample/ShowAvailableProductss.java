package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ShowAvailableProductss {
    @FXML
    private Button showAvailableProducts;
    @FXML
    private Button menu;

    TableView<Good> availableProducts = new TableView<>();

    public ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        for (int i = 0; i < Save.goods.size(); i++) {
            if (!Save.goods.get(i).goodAmount.startsWith("0")) {
                goods.add(Save.goods.get(i));
            }
        }
        return goods;
    }

    public void setTable() {

        availableProducts.getColumns().clear();

        TableColumn<Good, String> goodName = new TableColumn<>("NAME");
        goodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodName.setResizable(false);
        goodName.setMinWidth(200);
        TableColumn<Good, String> goodAmount = new TableColumn<>("AMOUNT");
        goodAmount.setCellValueFactory(new PropertyValueFactory<>("goodAmount"));
        goodAmount.setResizable(false);
        goodAmount.setMinWidth(200);
        TableColumn<Good, String> goodBuyPrice = new TableColumn<>("BUYPRICE");
        goodBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        goodBuyPrice.setResizable(false);
        goodBuyPrice.setMinWidth(200);
        TableColumn<Good, String> goodSellPrice = new TableColumn<>("SELLPRICE");
        goodSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        goodSellPrice.setResizable(false);
        goodSellPrice.setMinWidth(200);
        TableColumn<Good, String> goodCode = new TableColumn<>("CODE");
        goodCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        goodCode.setResizable(false);
        goodCode.setMinWidth(200);

        availableProducts.setItems(getGoods());
        availableProducts.getColumns().addAll(goodName, goodAmount, goodBuyPrice, goodSellPrice , goodCode);
    }

    public void showAvailableProducts(ActionEvent event) {

        setTable();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(availableProducts);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Available Products");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void back() throws IOException {
        MainSeller m = new MainSeller();
        m.changeScene("seller.fxml");
    }
}

