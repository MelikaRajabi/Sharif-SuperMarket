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

public class ShowOrders {

    @FXML
    private Button menu;
    @FXML
    private Button order;

    TableView<Order> orders = new TableView<>();

    public ObservableList<Order> getOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        if(Save.orders.size()<10){
            for (int i = Save.orders.size() - 1; i >= 0; i--) {
                orders.add(Save.orders.get(i));
            }
        }
        else {
            for (int i = Save.orders.size() - 1; i >= Save.orders.size() - 10; i--) {
                orders.add(Save.orders.get(i));
            }
        }
        return orders;
    }

    public void setTable() {

        orders.getColumns().clear();

        TableColumn<Order, String> customerId = new TableColumn<>("CUSTOMER ID");
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerId.setResizable(false);
        customerId.setMinWidth(200);
        TableColumn<Order, String> date = new TableColumn<>("DATE");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setResizable(false);
        date.setMinWidth(200);
        TableColumn<Order, String> goodCode = new TableColumn<>("GOOD CODE");
        goodCode.setCellValueFactory(new PropertyValueFactory<>("goodCode"));
        goodCode.setResizable(false);
        goodCode.setMinWidth(200);
        TableColumn<Order, String> goodAmount = new TableColumn<>("GOOD AMOUNT");
        goodAmount.setCellValueFactory(new PropertyValueFactory<>("goodAmount"));
        goodAmount.setResizable(false);
        goodAmount.setMinWidth(200);

        orders.setItems(getOrders());
        orders.getColumns().addAll(customerId ,date, goodCode , goodAmount);
    }

    public void showOrders(ActionEvent event) {

        setTable();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(orders);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Orders");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void back() throws IOException {
        MainSeller m = new MainSeller();
        m.changeScene("seller.fxml");
    }
}
