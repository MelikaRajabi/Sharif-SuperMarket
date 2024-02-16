package sample;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Order {
    String customerId;
    String date;
    String goodCode;
    String goodAmount;

    public Order (String customerId , String goodCode , String goodAmount){
        this.customerId = customerId;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString();
        this.goodCode = goodCode;
        this.goodAmount = goodAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public String getGoodAmount() {
        return goodAmount;
    }
}
