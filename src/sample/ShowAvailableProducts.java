package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class ShowAvailableProducts {

    @FXML
    private Button showAvailableProducts;
    @FXML
    private Button menu;
    @FXML
    private Label buyError;
    @FXML
    private Label buyError1;

    TableView<Good> availableProducts = new TableView<>();

    Scanner userScanner;
    {
        try {
            userScanner = new Scanner(LogIn.currentCustomer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String currentCustomer = userScanner.nextLine();

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
        TableColumn<Good, String> goodPrice = new TableColumn<>("PRICE");
        goodPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        goodPrice.setResizable(false);
        goodPrice.setMinWidth(200);
        TableColumn<Good, String> goodCode = new TableColumn<>("CODE");
        goodCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        goodCode.setResizable(false);
        goodCode.setMinWidth(200);

        availableProducts.setItems(getGoods());
        availableProducts.getColumns().addAll(goodName, goodAmount, goodPrice, goodCode);
    }

    public void showAvailableProducts(ActionEvent event) {

        setTable();

        TextField amount = new TextField();
        amount.setPromptText("Amount");
        amount.setMinSize(100, 25);
        TextField code = new TextField();
        code.setPromptText("Good Code");
        amount.setMinSize(100, 25);

        Button buy = new Button("   BUY    ");
        buy.setMinSize(25, 25);
        buy.setOnAction(e -> {
            boolean isError = false;
            buyError.setText("");
            buyError1.setText("");
            if(amount.getText().isEmpty()) {buyError.setText("Please enter the goodAmount ");isError = true;}
            if(code.getText().isEmpty()) {buyError1.setText("Please enter the code of the good ");isError = true;}
            try {
                if(!isError) toBuy(amount.getText(), code.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Button cancel = new Button("CANCEL");
        cancel.setMinSize(25,25);
        cancel.setOnAction(e -> {
            boolean isError = false;
            buyError.setText("");
            buyError1.setText("");
            if(amount.getText().isEmpty()) {buyError.setText("Please enter the goodAmount ");isError = true;}
            if(code.getText().isEmpty()) {buyError1.setText("Please enter the code of the good ");isError = true;}
            try {
                if(!isError)  toCancel(code.getText() , amount.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(availableProducts, amount, code, buy , cancel);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("AvailableProducts");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        try {
            Save.writeListOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Save.readListGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toBuy(String amount, String code) throws IOException {

        buyError.setText("");
        boolean GoodIsExist = false;

        for (int i = 0; i < Save.goods.size(); i++) {
            if (Save.goods.get(i).code.equals(code) && Integer.parseInt(Save.goods.get(i).goodAmount) >= Integer.parseInt(amount) && decreaseMoney(Save.goods.get(i).sellPrice , amount)) {
                GoodIsExist = true;
                Save.goods.get(i).goodAmount = String.valueOf(Integer.parseInt(Save.goods.get(i).goodAmount) - Integer.parseInt(amount));
                decreaseMoney(Save.goods.get(i).sellPrice , amount);
                Order order = new Order(currentCustomer, code , amount);
                Save.orders.add(order);
                setTable();
                break;}
            else if(Save.goods.get(i).code.equals(code) && Integer.parseInt(Save.goods.get(i).goodAmount) < Integer.parseInt(amount)){
                GoodIsExist = true;
                buyError.setText("More than available goodAmount requested");
            }
            else if(Save.goods.get(i).code.equals(code) &&  !(decreaseMoney(Save.goods.get(i).sellPrice , amount))){
                GoodIsExist = true;
                buyError.setText("Not enough money in your account");
            }
        }
        if(!GoodIsExist){
            buyError.setText("                 Invalid code");
        }
    }

    private boolean decreaseMoney(String goodPrice , String amount) throws IOException {

        Scanner scanner = new Scanner(LogIn.inputFile);
        Scanner overwriteScanner = new Scanner(LogIn.inputFile);

        StringBuffer buffer = new StringBuffer();
        while (overwriteScanner.hasNextLine()) {
            buffer.append(overwriteScanner.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        overwriteScanner.close();

        while(scanner.hasNextLine()){

            String s = scanner.nextLine();
            String[] split = s.split(" ");

          if(split[0].equals(currentCustomer)) {
              if (Integer.parseInt(split[2]) >= Integer.parseInt(goodPrice) * Integer.parseInt(amount)) {

                  fileContents = fileContents.replaceAll(split[2], String.valueOf(Integer.parseInt(split[2]) - Integer.parseInt(goodPrice) * Integer.parseInt(amount)));
                  FileWriter writer = new FileWriter(LogIn.inputFile);
                  writer.append(fileContents);
                  writer.flush();
                  return true;

              } else if (Integer.parseInt(split[2]) < Integer.parseInt(goodPrice)) {
                  return false;
              }
          }
        }
       return false;
    }

    public void toCancel(String code , String amount) throws IOException {
        boolean isFound = false;
        buyError.setText("");

        for (int i = 0; i < Save.orders.size(); i++) {
            if (Save.orders.get(i).customerId.equals(currentCustomer) && Save.orders.get(i).goodCode.equals(code)) {
                isFound = true;
            }
        }
        if (isFound) {
            for (int i = 0; i < Save.goods.size(); i++) {
                if (Save.goods.get(i).code.equals(code)) {
                    Save.goods.get(i).goodAmount = String.valueOf(Integer.parseInt(Save.goods.get(i).goodAmount) + Integer.parseInt(amount));
                    increaseMoney(Save.goods.get(i).sellPrice, Save.goods.get(i).goodAmount);
                    break;
                }
            }
            for (int i = 0; i < Save.orders.size(); i++) {
                if (Save.orders.get(i).goodCode.equals(code) && Save.orders.get(i).customerId.equals(currentCustomer)) {
                    Save.orders.remove(Save.orders.get(i));
                    break;
                }
            }

            setTable();

        }
        else {buyError.setText("This order does not exist");}
    }


    public void increaseMoney(String goodPrice , String amount) throws IOException {
        Scanner scanner = new Scanner(LogIn.inputFile);
        Scanner overwriteScanner = new Scanner(LogIn.inputFile);

        StringBuffer buffer = new StringBuffer();
        while (overwriteScanner.hasNextLine()) {
            buffer.append(overwriteScanner.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        overwriteScanner.close();

        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            if(split[0].equals(currentCustomer)){
                fileContents = fileContents.replaceAll(split[2],String.valueOf(Integer.parseInt(split[2]) + Integer.parseInt(goodPrice)*Integer.parseInt(amount)));
                FileWriter writer = new FileWriter(LogIn.inputFile);
                writer.append(fileContents);
                writer.flush();
                break;}}
    }

    public void back() throws IOException {
        MainCustomer m = new MainCustomer();
        m.changeScene("customer.fxml");
    }
}
