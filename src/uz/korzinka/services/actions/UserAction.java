package uz.korzinka.services.actions;

import uz.korzinka.helper.enums.UserRole;
import uz.korzinka.interfaceBuilder.builder.FourFunction;
import uz.korzinka.interfaceBuilder.builder.UserBuilder;
import uz.korzinka.model.user.User;

import java.util.List;
import java.util.Scanner;

public class UserAction {

    Scanner scannerStr = new Scanner(System.in);

    public List<User> register(List<User> lists, int listId) {

        System.out.println("Enter username");
        String username = scannerStr.nextLine();

        System.out.println("Enter password");
        String password = scannerStr.nextLine();
        if (!checkUser(lists, username)) {
            UserBuilder<User> userBuilder = User::new;
            lists.add(userBuilder.create(listId, username, password, UserRole.USER, 50000));
        }
        return lists;
    }

    public User login(List<User> lists) {

        System.out.println("Enter username");
        String username = scannerStr.nextLine();
        System.out.println("Enter password");
        String password = scannerStr.nextLine();

        FourFunction<User, String> returnUser = (userName, passWord, userlist) -> {
            User user = null;
            user = userlist.stream()
                    .filter(user1 -> user1.getUsername().equalsIgnoreCase(userName) && user1.getPassword().equals(passWord))
                    .findFirst()
                    .orElse(null);
            return user;
        };

        return returnUser.fourFunction(username, password, lists);
    }

    public boolean checkUser(List<User> lists, String username) {
        for (User list : lists) {
            if (list.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }

}
