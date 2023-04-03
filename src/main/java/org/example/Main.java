package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        Basket basket;
        ClientLog clientLog = new ClientLog();
        Product[] products = new Product[3];
        products[0] = new Product("Milk", 80);
        products[1] = new Product("Bread", 50);
        products[2] = new Product("Meat", 180);
        basket = Basket.loadFromTxtFile();
        if (basket == null){
            basket = new Basket(products);
        }
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String inputString = input.nextLine();
            if (inputString.equals("end")) {
                System.out.println("Ваша корзина:");
                basket.printCart();
                basket.saveTxt();

                break;
            } else {
                String[] product = inputString.split(" ");
                int productNumber = Integer.parseInt(product[0]) - 1;
                int productCount = Integer.parseInt(product[1]);
                if (productNumber >= 0 & productNumber < products.length) {
                    basket.addToCart(productNumber, productCount);
                    clientLog.exportAsCSV(products[productNumber].getProductName(), productCount);
                    System.out.println(products[productNumber] + " добавлен в кол-ве " + productCount);
                } else {
                    System.out.println("Продукт с данным номер не существует");
                }
            }
        }
    }
}