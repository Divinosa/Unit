package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class Basket implements Serializable {
    private static final long serialVersionUID = 2L;
    public Product[] cart;

    public Basket(Product[] products) {
        cart = Arrays.copyOf(products, products.length);
    }

    public void addToCart(int productNumber, int productCount) {
        cart[productNumber].setProductCount(productCount);
    }

    public void printCart() {
        for (Product product : cart) {
            System.out.println(product.toStringChe());
        }

    }

    public void saveTxt() throws Exception {
        Shop shop = new Shop("save");
        if (Shop.active) {
            if (Shop.format == 1) {
                FileOutputStream out = new FileOutputStream(Shop.fileName);
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                out.write(gson.toJson(cart).getBytes(StandardCharsets.UTF_8));
                out.close();
            } else if (Shop.format == 2) {
                FileOutputStream out = new FileOutputStream(Shop.fileName);
                ObjectOutputStream obj = new ObjectOutputStream(out);
                obj.writeObject(this);
                obj.close();
            } else throw new NumberFormatException("Не верный формат файла " + Shop.format);

        }
    }

    static Basket loadFromTxtFile() throws Exception {
        Shop shop = new Shop("load");
        Basket loadBasket = null;
        if (Shop.active) {
            try {
                FileInputStream file = new FileInputStream(Shop.fileName);
                if (Shop.format == 1) {
                    int i;
                    StringBuilder initLoad = new StringBuilder();
                    while ((i = file.read()) != -1) {
                        initLoad.append(Character.toChars(i));
                    }
                    file.close();
                    String loadJsonText = initLoad.toString();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    loadBasket = new Basket(gson.fromJson(loadJsonText, Product[].class));
                } else if (Shop.format == 2) {
                    ObjectInputStream obf = new ObjectInputStream(file);
                    Basket basket = (Basket) obf.readObject();
                    loadBasket = new Basket(basket.cart);
                } else throw new NumberFormatException("Не верный формат файла " + Shop.format);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        return loadBasket;
    }
}
