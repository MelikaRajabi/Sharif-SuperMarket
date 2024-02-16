package sample;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Save {
    public static ArrayList<Good> goods = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();

    public static void writeListGoods() throws IOException {
        Gson gson = new Gson();
        Writer writer = Files.newBufferedWriter(Paths.get("goodsList.json"));
        gson.toJson(Save.goods, writer);
        writer.close();

    }

    public static void readListGoods() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("goodsList.json"));
        goods.clear();
        List<Good> temp = Arrays.asList(gson.fromJson(reader, Good[].class));
        goods.addAll(temp);
        reader.close();
    }

    public static void writeListOrders() throws IOException {
        Gson gson = new Gson();
        Writer writer = Files.newBufferedWriter(Paths.get("ordersList.json"));
        gson.toJson(Save.orders, writer);
        writer.close();

    }

    public static void readListOrders() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("ordersList.json"));
        orders.clear();
        List<Order> temp = Arrays.asList(gson.fromJson(reader, Order[].class));
        orders.addAll(temp);
        reader.close();
    }
   }
