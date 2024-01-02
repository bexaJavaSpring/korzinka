package uz.korzinka.services;

import uz.korzinka.helper.enums.UserRole;
import uz.korzinka.helper.messages.MessageHelper;
import uz.korzinka.interfaceBuilder.UserInterface;
import uz.korzinka.interfaceBuilder.builder.UserBuilder;
import uz.korzinka.model.history.History;
import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;
import uz.korzinka.services.actions.GroupAction;
import uz.korzinka.services.actions.UserAction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements UserInterface {

    List<User> users = new ArrayList<>();
    int userId = 1001;

    List<Product> groupHistories = new ArrayList<>();
    List<History> userHistory = new ArrayList<>();
    int userHistoryId = 5000;

    public UserService() {
        UserBuilder<User> admin = User::new;
        users.add(admin.create(userId, "Bekhruz", "0000", UserRole.ADMIN, 10000));
    }

    @Override
    public void signUp() {
        UserAction userAction = new UserAction();
        List<User> oldusers = new ArrayList<>();
        oldusers.addAll(users);
        users = userAction.register(users, userId);
        if (users.size() > oldusers.size()) {
            userId++;
            System.out.println(MessageHelper.CREATED_USER);
        } else {
            System.out.println(MessageHelper.USER_ALERTEST);
        }
    }

    @Override
    public User signIn() {
        UserAction userAction = new UserAction();
        User user = userAction.login(users);
        return user;
    }

    @Override
    public void userList() {
        System.out.println("| username |  role");
        users.forEach(user -> System.out.println("| " + user.getUsername() + "  " + user.getRole()));
    }

    public void buyProduct(User user, Product product) {
        GroupAction groupAction = new GroupAction();
        Product product1 =
                groupAction.buyProduct(product, user, users);

        if (product1 != null) {
            History history = new History(userHistoryId, user.getId(), product1);
            userHistory.add(history);
            userHistoryId++;

            groupHistories.add(product1);
            System.out.println(MessageHelper.BUY_SUCCESS);
        } else {
            System.out.println(MessageHelper.WRONG_OPTION);
        }

    }

    public void sellingHistory() {
        System.out.println("All time stats");
        groupHistories.stream()
                .collect(Collectors.groupingBy(product -> product.getId(),
                        Collectors.summingInt(product -> (int) product.getQuantity())))
                .forEach((id, quantity) -> {
                            Product product = findProduct(id);
                            System.out.println("name : " + product.getProductName() + ", price : "
                                    + product.getPrice() +
                                    ", total quantity : " + quantity +
                                    ", totalPrice : " + (product.getPrice() * quantity));
                        }
                );
    }

    public Product findProduct(int id) {
        for (Product groupHistory : groupHistories) {
            if (groupHistory.getId() == id)
                return groupHistory;
        }
        return null;
    }

    public void sellingHistoryByUser(User user) {
        if (user.getRole().equals(UserRole.ADMIN)) {
            for (User user1 : users) {
                boolean isFound = false;
                for (History history : userHistory) {
                    if (history.getUser_id() == user1.getId()) {
                        System.out.println("name : " + history.getProduct().getProductName() +
                                ", quantity : " + history.getProduct().getQuantity() +
                                ", price : " + history.getProduct().getPrice());
                        isFound = true;
                    }
                }
                String a = isFound ? "User =>" + user1.getUsername() : "";
                System.out.println(a + "\n");
            }
        } else {
            System.out.println("\nUser : " + user.getUsername());
            for (History history : userHistory) {
                if (history.getUser_id() == user.getId()) {
                    System.out.println("name : " + history.getProduct().getProductName() +
                            ", quantity : " + history.getProduct().getQuantity() +
                            ", price : " + history.getProduct().getPrice());
                }
            }
        }
    }

}
