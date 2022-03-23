package uz.korzinka.services.actions;

import uz.korzinka.helper.enums.UserRole;
import uz.korzinka.helper.messages.MessageHelper;
import uz.korzinka.interfaceBuilder.builder.ProductBuilder;
import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class MarketAction {

    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerDou = new Scanner(System.in);

    public TreeMap<String, Product> addProduct(TreeMap<String, Product> productHashMap, int productId) {

        System.out.println("Enter product name");
        String name = scannerStr.nextLine();

        System.out.println("Enter product price");
        double price = scannerDou.nextDouble();

        System.out.println("Enter product quantity");
        double quantity = scannerDou.nextDouble();

        if (!checkProduct(productHashMap, name)) {
            ProductBuilder<Product> productBuilder = Product::new;
            productHashMap.put(name, productBuilder.create(productId, name, price, quantity));
        }

        return productHashMap;
    }

    private boolean checkProduct(TreeMap<String, Product> productHashMap, String name) {

        for (String productName : productHashMap.keySet()) {
            if (productName.equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public void editPrice(TreeMap<String, Product> productHashMap) {

        System.out.println("Enter product name");
        String name = scannerStr.nextLine();

        for (String countryName : productHashMap.keySet()) {
            if (countryName.equalsIgnoreCase(name)) {
                Product product = productHashMap.get(name);
                System.out.println("Enter price");
                double price = scannerDou.nextDouble();
                ProductBuilder<Product> productBuilder = Product::new;
                productHashMap.put(name, productBuilder.create(product.getId(), name, price, product.getQuantity()));
                System.out.println(MessageHelper.EDITED_PRICE);
                return;
            }
        }
        System.out.println(MessageHelper.PRODUCT_NOT);
    }

    public void addQuantity(TreeMap<String, Product> productHashMap) {
        System.out.println("Enter product name");
        String name = scannerStr.nextLine();
        for (String countryName : productHashMap.keySet()) {
            if (countryName.equalsIgnoreCase(name)) {
                Product product = productHashMap.get(name);
                System.out.println("Enter quantity");
                double quantity = scannerDou.nextDouble();
                double total = product.getQuantity() + quantity;
                ProductBuilder<Product> productBuilder = Product::new;
                productHashMap.put(name, productBuilder.create(product.getId(), name, product.getPrice(), total));
                System.out.println(MessageHelper.ADD_QTY);
                return;
            }
        }
        System.out.println(MessageHelper.PRODUCT_NOT);
    }

    public Product buyProduct(Product product,
                              User user,
                              List<User> users) {

        System.out.println("How much");
        double quantity = scannerDou.nextDouble();

        if (quantity <= product.getQuantity() && user.getBalance() >= (product.getPrice() * quantity)) {
            user.setBalance(user.getBalance() - (product.getPrice() * quantity));
            product.setQuantity(product.getQuantity() - quantity);
            users.stream().filter(user1 -> user1.getRole().equals(UserRole.ADMIN))
                    .forEach(user1 -> user1.setBalance(user1.getBalance() + (product.getPrice() * quantity)));

            ProductBuilder<Product> productBuilder = Product::new;
            System.out.println("Total : " + (product.getPrice() * quantity));
            return productBuilder.create(product.getId(), product.getProductName(), product.getPrice(), quantity);
        }

        return null;

    }

}
