package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ShowProducts {

    @FXML
    private Button menu;
    @FXML
    private Button showProducts;
    @FXML
    private Label Error;

    TableView<Good> products = new TableView<>();

    public ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        for (int i = 0; i < Save.goods.size(); i++) {
                goods.add(Save.goods.get(i));
            }
        return goods;
    }

    public void setTable() {

        products.getColumns().clear();

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

        products.setItems(getGoods());
        products.getColumns().addAll(goodName, goodAmount, goodBuyPrice, goodSellPrice , goodCode);
    }

    public void showProducts(ActionEvent event) {

        setTable();

        Button add = new Button("    ADD   ");
        add.setMinSize(25, 25);
        add.setOnAction(e -> add());

        Button remove = new Button("REMOVE");
        remove.setMinSize(25, 25);
        remove.setOnAction(e -> remove());

        Button edit = new Button("    EDIT   ");
        edit.setMinSize(25, 25);
        edit.setOnAction(e -> edit());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(products , add , remove , edit );
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

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
    }

    public void back() throws IOException {
        MainSeller m = new MainSeller();
        m.changeScene("seller.fxml");
    }

    public void add(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));

        Label name = new Label("Name : ");
        GridPane.setConstraints(name , 0 ,0);
        TextField goodName = new TextField();
        GridPane.setConstraints(goodName , 2,0);

        Label amount = new Label("Amount : ");
        GridPane.setConstraints(amount , 0,1);
        TextField goodAmount = new TextField();
        GridPane.setConstraints(goodAmount , 2,1);

        Label buyPrice = new Label("BuyPrice : ");
        GridPane.setConstraints(buyPrice , 0,2);
        TextField goodBuyPrice = new TextField();
        GridPane.setConstraints(goodBuyPrice , 2,2);

        Label sellPrice = new Label("SellPrice : ");
        GridPane.setConstraints(sellPrice , 0,3);
        TextField goodSellPrice = new TextField();
        GridPane.setConstraints(goodSellPrice , 2,3);

        Label code = new Label("Code : ");
        GridPane.setConstraints(code , 0,4);
        TextField goodCode = new TextField();
        GridPane.setConstraints(goodCode , 2,4);

        Button add = new Button("ADD");
        GridPane.setConstraints(add , 1 , 5);
        add.setOnAction(e -> {
            boolean isError = false;
            Error.setText("");
            if(goodName.getText().isEmpty()||goodAmount.getText().isEmpty()||goodBuyPrice.getText().isEmpty()||goodSellPrice.getText().isEmpty()||goodCode.getText().isEmpty()){
               isError = true;
                Error.setText("Please complete all fields");
            }
            if(Save.goods.size()==0 && (Integer.parseInt(goodBuyPrice.getText()) > Integer.parseInt(goodSellPrice.getText())))
            {   isError = true;
                Error.setText("             Invalid Input");
            }
            for (int i = 0; i < Save.goods.size(); i++) {
                if (goodName.getText().equals(Save.goods.get(i).name) || (Integer.parseInt(goodBuyPrice.getText()) > Integer.parseInt(goodSellPrice.getText()))) {
                    isError = true;
                    Error.setText("             Invalid Input");
                }
            }
             if(!isError) {
            Save.goods.add(new Good(goodName.getText() , goodAmount.getText() , goodBuyPrice.getText() , goodSellPrice.getText() , goodCode.getText()));
            setTable();}
        });

        gridPane.getChildren().addAll(name , goodName , amount , goodAmount , buyPrice , goodBuyPrice , sellPrice , goodSellPrice , code , goodCode , add);
        Scene scene = new Scene(gridPane);
        Stage stage = new Stage();
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void remove(){

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));

        Label name = new Label("Name : ");
        GridPane.setConstraints(name , 0 ,0);
        TextField goodName = new TextField();
        GridPane.setConstraints(goodName , 2,0);

        Button remove = new Button("REMOVE");
        GridPane.setConstraints(remove , 1 , 1);
        remove.setOnAction(e -> {
            Error.setText("");
            boolean isFound = false;
            if(goodName.getText().isEmpty()){
                Error.setText("Please complete all fields");
                return;
            }
            for (int i = 0; i < Save.goods.size(); i++) {
                if (Save.goods.get(i).name.equals(goodName.getText())) {
                    isFound = true;
                    Save.goods.remove(i);
                    setTable();
                    break;
                }}
        if(!isFound){
            Error.setText("             Invalid Input");
        }});

        gridPane.getChildren().addAll(name , goodName , remove );
        Scene scene = new Scene(gridPane);
        Stage stage = new Stage();
        stage.setTitle("Remove");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void edit(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));

        Label name = new Label("Name : ");
        GridPane.setConstraints(name , 0 ,0);
        TextField goodName = new TextField();
        GridPane.setConstraints(goodName , 2,0);

        Label amount = new Label("Amount : ");
        GridPane.setConstraints(amount , 0,1);
        TextField goodAmount = new TextField();
        GridPane.setConstraints(goodAmount , 2,1);

        Label buyPrice = new Label("BuyPrice : ");
        GridPane.setConstraints(buyPrice , 0,2);
        TextField goodBuyPrice = new TextField();
        GridPane.setConstraints(goodBuyPrice , 2,2);

        Label sellPrice = new Label("SellPrice : ");
        GridPane.setConstraints(sellPrice , 0,3);
        TextField goodSellPrice = new TextField();
        GridPane.setConstraints(goodSellPrice , 2,3);

        Label code = new Label("Code : ");
        GridPane.setConstraints(code , 0,4);
        TextField goodCode = new TextField();
        GridPane.setConstraints(goodCode , 2,4);

        Button edit = new Button("EDIT");
        GridPane.setConstraints(edit , 1 , 5);
        edit.setOnAction(e -> {
            boolean isError = false;
            boolean isFound = false;
            Error.setText("");
            if(goodName.getText().isEmpty()||goodAmount.getText().isEmpty()||goodBuyPrice.getText().isEmpty()||goodSellPrice.getText().isEmpty()||goodCode.getText().isEmpty()){
                isError = true;
                Error.setText("Please complete all fields");
                return;
            }
            for(int i=0;i<Save.goods.size();i++){
                if(Save.goods.get(i).name.equals(goodName.getText())){
                    isFound = true;
                    break;
                }
            }
            if(!isFound || Integer.parseInt(goodBuyPrice.getText()) > Integer.parseInt(goodSellPrice.getText())){
                Error.setText("              Invalid input");
                return;
            }
            if(!isError && isFound) {
                for(int i=0;i<Save.goods.size();i++){
                    if(Save.goods.get(i).name.equals(goodName.getText())){
                        Save.goods.remove(Save.goods.get(i));
                        break;
                    }
                }
                Save.goods.add(new Good(goodName.getText() , goodAmount.getText() , goodBuyPrice.getText() , goodSellPrice.getText() , goodCode.getText()));
                setTable();}
        });

        gridPane.getChildren().addAll(name , goodName , amount , goodAmount , buyPrice , goodBuyPrice , sellPrice , goodSellPrice , code , goodCode , edit);
        Scene scene = new Scene(gridPane);
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
