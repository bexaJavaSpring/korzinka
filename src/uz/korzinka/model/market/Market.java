package uz.korzinka.model.market;

import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;

import java.util.List;
import java.util.TreeMap;

public class Market {

    private Integer id;
    private String marketName;
    private TreeMap<String, Product> products;
    private List<User> users;
    private double balance;

    public Market() {
    }

    public Market(Integer id, String marketName, TreeMap<String, Product> products, List<User> users, double balance) {
        this.id = id;
        this.marketName = marketName;
        this.products = products;
        this.users = users;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public TreeMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(TreeMap<String, Product> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", marketName='" + marketName + '\'' +
                ", products=" + products +
                ", users=" + users +
                ", balance=" + balance +
                '}';
    }
}
