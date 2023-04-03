package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BasketTest {

    @Test(expected = NumberFormatException.class)
    public void saveTxt_loadFromTxtFile() throws Exception {
        Basket basket = new Basket(new Product[]{new Product("Milk", 80)});
        basket.saveTxt();
        Basket basket1 = Basket.loadFromTxtFile();
    }

    @Test(expected = FileNotFoundException.class) // не понимаю почему тут не работает....
    public void loadFromTxtFileFile() throws Exception {
        Basket basket = Basket.loadFromTxtFile();
    }

    @Test
    public void BasketNotSame() {
        Product[] products = new Product[]{new Product("Milk", 80)};
        Basket expected = new Basket(products);
        Basket actual = new Basket(products);

        Assert.assertNotSame(expected, actual);
    }
}