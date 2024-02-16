package sample;

public class Good {

    public String name;
    public String goodAmount;
    public String buyPrice;
    public String sellPrice;
    public String code;
    public String profit;
    public String numberOfOrders;
    public String totalSellPrice;



    public Good(String name, String goodAmount, String buyPrice, String sellPrice , String code) {
        this.name = name;
        this.goodAmount = goodAmount;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.code = code;
        this.profit = String.valueOf(Integer.parseInt(this.sellPrice) - Integer.parseInt(this.buyPrice));
        this.numberOfOrders = String.valueOf(numberOfOrders());
        this.totalSellPrice = String.valueOf(Integer.parseInt(this.sellPrice) * Integer.parseInt(this.goodAmount));

    }

    public String getName() {
        return name;
    }

    public String getGoodAmount() {
        return goodAmount;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public String getCode() {
        return code;
    }

    public String getProfit() {
        return profit;
    }

    public String getNumberOfOrders() {
        return numberOfOrders;
    }

    public String getTotalSellPrice() {
        return totalSellPrice;
    }

    public int numberOfOrders(){
        int numberOfOrders = 0 ;
        for(int i=0;i<Save.orders.size();i++){
            if(Save.orders.get(i).goodCode.equals(this.code)){
                numberOfOrders+=Integer.parseInt(Save.orders.get(i).goodAmount);
            }
        }
        return numberOfOrders;
    }
}
