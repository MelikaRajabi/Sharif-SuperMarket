package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.*;
import java.util.Scanner;

public class LogIn {

    @FXML
    private Button logIn;
    @FXML
    private Button register;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Label emptyUsername;
    @FXML
    private Label emptyPassword;
    @FXML
    private Label wrongLogIn;

    public static File inputFile = new File("D:\\movaghat\\SharifMarketGrafic\\src\\File\\USERDATA.txt");
    public static File currentCustomer = new File("D:\\movaghat\\SharifMarketGrafic\\src\\File\\currentCustomer.txt");

    public void logIn() throws IOException {

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


        PrintWriter writer = new PrintWriter(currentCustomer);
        writer.print("");
        writer.close();

        FileWriter out = new FileWriter(currentCustomer.getAbsoluteFile(), true);
        out.write(Username.getText());
        out.close();

        Scanner scanner = new Scanner(inputFile);
        emptyUsername.setText("");
        emptyPassword.setText("");
        wrongLogIn.setText("");
        boolean userIsExist = false;

        if (Username.getText().isEmpty()) {
            userIsExist = true;
            emptyUsername.setText("Please enter your username");
        }
        if (Password.getText().isEmpty()) {
            userIsExist = true;
            emptyPassword.setText("Please enter your password");
        }

        while (scanner.hasNextLine()) {

            String s = scanner.nextLine();
            String[] split = s.split(" ");

            if (Username.getText().equals(split[0]) && !(Password.getText().equals(split[1])) && !(Password.getText().isEmpty())) {
                userIsExist = true;
                wrongLogIn.setText("Incorrect password");
            }
            if (Username.getText().equals(split[0]) && Password.getText().equals(split[1])) {
                userIsExist = true;
                MainCustomer m = new MainCustomer();
                m.changeScene("customer.fxml");
            }
        }
        if (!userIsExist) {
            wrongLogIn.setText("You should register first");
        }
    }


    public void register() throws IOException {

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

        PrintWriter writer = new PrintWriter(currentCustomer);
        writer.print("");
        writer.close();

        FileWriter output = new FileWriter(currentCustomer.getAbsoluteFile(), true);
        output.write(Username.getText());
        output.close();

        Scanner scanner = new Scanner(inputFile);
        emptyUsername.setText("");
        emptyPassword.setText("");
        wrongLogIn.setText("");
        boolean userIsExist = false;

        if (Username.getText().isEmpty()) {
            emptyUsername.setText("Please enter your username");
        }
        if (Password.getText().isEmpty()) {
            emptyPassword.setText("Please enter your password");
        }

        while (scanner.hasNextLine()) {

            String s = scanner.nextLine();
            String[] split = s.split(" ");
            if (Username.getText().equals(split[0])) {
                userIsExist = true;
                break;
            }
            }

        if (!Password.getText().isEmpty() && !Username.getText().isEmpty() && !userIsExist) {

            FileWriter out = new FileWriter(inputFile.getAbsoluteFile(), true);
            out.write(Username.getText());
            out.write(" ");
            out.write(Password.getText());
            out.write(" ");
            out.write("1000000");
            out.write("\n");
            out.close();

            MainCustomer m = new MainCustomer();
            m.changeScene("customer.fxml");
        }

        if (userIsExist) {
            wrongLogIn.setText("This user has already registered");
        }}

    }

