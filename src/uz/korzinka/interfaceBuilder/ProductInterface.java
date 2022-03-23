package uz.korzinka.interfaceBuilder;

import uz.korzinka.model.user.User;

public interface ProductInterface {

    void addProduct();

    void editPriceProduct();

    void addQuantityProduct();

    void buyProduct(User user);

    void removeProduct();

}
