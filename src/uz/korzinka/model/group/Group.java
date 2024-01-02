package uz.korzinka.model.group;

import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;

import java.util.List;
import java.util.TreeMap;

public class Group {

    private Integer id;
    private String groupName;
    private TreeMap<String, Product> products;
    private List<User> users;
    private double balance;

    public Group() {
    }

    public Group(Integer id, String groupName, TreeMap<String, Product> products, List<User> users, double balance) {
        this.id = id;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", products=" + products +
                ", users=" + users +
                ", balance=" + balance +
                '}';
    }
}
