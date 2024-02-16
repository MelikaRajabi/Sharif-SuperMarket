package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ShowStatistics {

    @FXML
    private Button showStatistics;
    @FXML
    private Button menu;
    @FXML
    private Label calculate;

    public TableView<Good> totalSell = new TableView<>();

    public void showStatistics(){
        GridPane gridPane = new GridPane();

        Button totalSell = new Button("  TOTAL SELL  ");
        GridPane.setConstraints(totalSell , 0 , 0);
        totalSell.setOnAction(e -> totalSell());
        Button totalProfit = new Button("TOTAL PROFIT");
        GridPane.setConstraints(totalProfit , 0 , 1);
        totalProfit.setOnAction(e -> totalProfit());
        Button eachSell = new Button("  SELECTED PRODUCT SELL  ");
        GridPane.setConstraints(eachSell , 1 , 0);
        eachSell.setOnAction(e -> eachSell());
        Button eachProfit = new Button("SELECTED PRODUCT PROFIT");
        GridPane.setConstraints(eachProfit , 1 , 1);
        eachProfit.setOnAction(e -> eachProfit());

        gridPane.getChildren().addAll(totalSell , totalProfit , eachSell , eachProfit);
        Scene scene = new Scene(gridPane);
        Stage stage = new Stage();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        for (int i = 0; i < Save.goods.size(); i++) {
            goods.add(Save.goods.get(i));
        }
        return goods;
    }

    public void setTable() {

        totalSell.getColumns().clear();

        TableColumn<Good, String> goodName = new TableColumn<>("NAME");
        goodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodName.setResizable(false);
        goodName.setMinWidth(200);
        TableColumn<Good, String> numberOfOrders = new TableColumn<>("ORDER NUMBERS");
        numberOfOrders.setCellValueFactory(new PropertyValueFactory<>("numberOfOrders"));
        numberOfOrders.setResizable(false);
        numberOfOrders.setMinWidth(200);
        TableColumn<Good, String> goodAmount = new TableColumn<>("AMOUNT");
        goodAmount.setCellValueFactory(new PropertyValueFactory<>("goodAmount"));
        goodAmount.setResizable(false);
        goodAmount.setMinWidth(200);
        TableColumn<Good, String> profit = new TableColumn<>("TOTAL PROFIT");
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        profit.setResizable(false);
        profit.setMinWidth(200);
        TableColumn<Good, String> totalSellPrice = new TableColumn<>("TOTAL SELL PRICE");
        totalSellPrice.setCellValueFactory(new PropertyValueFactory<>("totalSellPrice"));
        totalSellPrice.setResizable(false);
        totalSellPrice.setMinWidth(200);

        totalSell.setItems(getGoods());
        totalSell.getColumns().addAll(goodName , numberOfOrders, goodAmount, profit, totalSellPrice);
    }


    public void totalSell(){

        setTable();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(totalSell);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void totalProfit(){
        int profit = 0;
        for (int i = 0; i < Save.goods.size(); i++) {
            profit += Integer.parseInt(Save.goods.get(i).goodAmount)*(Integer.parseInt(Save.goods.get(i).sellPrice) - Integer.parseInt(Save.goods.get(i).buyPrice));
        }
        calculate.setText("Total profit is : " + profit);
    }

    public void eachSell() {

        TextField productSelected = new TextField();
        productSelected.setPromptText("name");

        Label orderNumbers = new Label();
        Label goodNumbers = new Label();
        Label totalSellPrice = new Label();
        Label totalProfit = new Label();

        Button enter = new Button("                      ENTER                     ");
        enter.setOnAction(e -> {
            calculate.setText("");
            if (productSelected.getText().isEmpty()) {
                calculate.setText("Please fill the text field");
                return;
            }
            boolean isFound = false;
            int numberOfOrders = 0 ;
            for(int i=0;i<Save.goods.size();i++){
                if(Save.goods.get(i).name.equals(productSelected.getText())) {
                    for(int j=0;j<Save.orders.size();j++){
                       if(Save.orders.get(j).goodCode.equals(Save.goods.get(i).code)){
                           numberOfOrders+=Integer.parseInt(Save.orders.get(j).goodAmount);
                       }
                    }
                        }
                    }
            for (int i = 0; i < Save.goods.size(); i++) {
                if (Save.goods.get(i).name.equals(productSelected.getText())) {
                    orderNumbers.setText("Number of orders : " + numberOfOrders);
                    goodNumbers.setText("Number of " + productSelected.getText() + " : " + Save.goods.get(i).goodAmount);
                    totalSellPrice.setText("Total sell price : " + Integer.parseInt(Save.goods.get(i).sellPrice) * Integer.parseInt(Save.goods.get(i).goodAmount) + " IRR sell");
                    totalProfit.setText("Total profit : " + (Integer.parseInt(Save.goods.get(i).sellPrice) - Integer.parseInt(Save.goods.get(i).buyPrice)) * Integer.parseInt(Save.goods.get(i).goodAmount) + " IRR profit");
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                calculate.setText("Invalid input");
                return;
            }

        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(productSelected, enter, orderNumbers, goodNumbers, totalSellPrice, totalProfit);
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void eachProfit(){

        TextField productSelected = new TextField();
        productSelected.setPromptText("name");
        Button enter = new Button("                      ENTER                     ");
        enter.setOnAction(e -> {
            int profit = 0;
            boolean isFound = false;
            calculate.setText("");
            if(productSelected.getText().isEmpty()) {calculate.setText("Please fill the text field"); return;}
            for (int i = 0; i < Save.goods.size(); i++) {
                if (Save.goods.get(i).name.equals(productSelected.getText())) {
                    profit = Integer.parseInt(Save.goods.get(i).goodAmount) * (Integer.parseInt(Save.goods.get(i).sellPrice) - Integer.parseInt(Save.goods.get(i).buyPrice));
                    calculate.setText("Total profit of this good is : " + profit);
                    isFound = true;
                    break;
                }
            }
            if(!isFound){calculate.setText("Invalid input"); return;}
            });

        VBox vbox = new VBox(productSelected , enter);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Profit");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void back() throws IOException {
        MainSeller m = new MainSeller();
        m.changeScene("seller.fxml");
    }
}
