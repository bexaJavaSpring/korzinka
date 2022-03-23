package uz.korzinka.interfaceBuilder.builder;


@FunctionalInterface
public interface ProductBuilder<T> {

    T create(Integer id, String productName, double price, double quantity);
}
