package uz.korzinka.services;

import uz.korzinka.helper.messages.MessageHelper;
import uz.korzinka.interfaceBuilder.MarketInterface;
import uz.korzinka.interfaceBuilder.ProductInterface;
import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;
import uz.korzinka.services.actions.MarketAction;

import java.util.Scanner;
import java.util.TreeMap;

public class MarketService implements MarketInterface, ProductInterface {

    Scanner scannerStr = new Scanner(System.in);

    TreeMap<String, Product> products = new TreeMap<>(String::compareTo);
    int productId = 2000;

    @Override
    public void productList() {
        System.out.println("..");
        products.forEach((s, product) ->
                System.out.println("| name : " + s +
                        ", price : " + product.getPrice() +
                        ", quantity : " + product.getQuantity()));
    }

    @Override
    public void sellingHistoryByUser(User user) {

    }

    @Override
    public void addProduct() {
        MarketAction marketAction = new MarketAction();
        TreeMap<String, Product> oldProducts = new TreeMap<>();
        oldProducts.putAll(products);
        products = marketAction.addProduct(products, productId);
        if (products.size() > oldProducts.size()) {
            productId++;
            System.out.println(MessageHelper.PRODUCT_ADDED);
        } else {
            System.out.println(MessageHelper.PRODUCT_ALERTEST);
        }
    }

    @Override
    public void editPriceProduct() {
        productList();
        MarketAction marketAction = new MarketAction();
        marketAction.editPrice(products);
    }

    @Override
    public void addQuantityProduct() {
        productList();
        MarketAction marketAction = new MarketAction();
        marketAction.addQuantity(products);
    }

    @Override
    public void buyProduct(User user) {

    }

    @Override
    public void removeProduct() {
        productList();
        Product product = findProduct();
        products.remove(product.getProductName());
        System.out.println(MessageHelper.REMOVED);
    }

    public Product findProduct() {
        productList();
        System.out.println("Enter product name");
        String name = scannerStr.nextLine();

        for (String namepROD : products.keySet()) {
            if (namepROD.equalsIgnoreCase(name)) {
                Product product = products.get(name);
                return product;
            }
        }

        return null;
    }

}
