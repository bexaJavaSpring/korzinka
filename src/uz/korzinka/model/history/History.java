package uz.korzinka.model.history;

import uz.korzinka.model.product.Product;

public class History {

    private Integer id;
    private Integer user_id;
    private Product product;

    public History() {
    }

    public History(Integer id, Integer user_id, Product product) {
        this.id = id;
        this.user_id = user_id;
        this.product = product;
    }

    public History(int historyId, Product product1) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product=" + product +
                '}';
    }
}
