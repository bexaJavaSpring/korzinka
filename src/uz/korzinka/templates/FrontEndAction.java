package uz.korzinka.templates;

import uz.korzinka.helper.enums.UserRole;
import uz.korzinka.helper.messages.MessageHelper;
import uz.korzinka.model.product.Product;
import uz.korzinka.model.user.User;
import uz.korzinka.services.MarketService;
import uz.korzinka.services.UserService;

import java.util.Scanner;
import java.util.function.Predicate;

public class FrontEndAction {

    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    public void frontTemplate() {
        UserService userService = new UserService();
        MarketService marketService = new MarketService();

        boolean active = true;
        while (active) {
            System.out.println("1=> Sign In. 2=> Sign Up. 0=> Exit");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1: {
                    User user = userService.signIn();
                    if (user != null) {
                        if (user.getRole().equals(UserRole.ADMIN)) {
                            adminMenu(userService, marketService, user);
                        } else {
                            userMenu(userService, marketService, user);
                        }

                    } else {
                        System.out.println(MessageHelper.USER_NOT_FOUND);
                    }
                    break;
                }
                case 2: {
                    userService.signUp();
                    break;
                }
                case 0: {
                    active = false;
                    break;
                }
                default: {
                    System.out.println(MessageHelper.WRONG_OPTION);
                    break;
                }
            }

        }

    }

    private void userMenu(UserService userService, MarketService marketService, User user) {
        System.out.println(MessageHelper.LOGIN + " " + user.getUsername());
        boolean userMenu = true;
        while (userMenu) {
            System.out.println("1=> Buy Products. 2=> Balance. 3=>History. 4.Logout");
            int userOption = scannerInt.nextInt();
            switch (userOption) {
                case 1:
                    Product product = marketService.findProduct();
                    if (product != null) {
                        userService.buyProduct(user, product);
                    } else {
                        System.out.println(MessageHelper.PRODUCT_NOT);
                    }
                    break;
                case 2:
                    System.out.println("Balance : " + user.getBalance());
                    break;
                case 3:
                    System.out.println("Bought broducts");
                    userService.sellingHistoryByUser(user);
                    break;
                case 4:
                    userMenu = false;
                    break;
                default:
                    System.out.println(MessageHelper.WRONG_OPTION);
                    break;
            }
        }
    }

    private void adminMenu(UserService userService, MarketService marketService, User user) {
        System.out.println("Admin " + user.getUsername());
        boolean adminactive = true;
        while (adminactive) {
            System.out.println("1=> Product List. 2=> Product Settings. 3=> UserList");
            System.out.println("4=> SellingHistory. 5=>UserHistory. 6=>Balance. 7=>Logout");
            int adminOption = scannerInt.nextInt();
            switch (adminOption) {
                case 1: {
                    marketService.productList();
                    break;
                }
                case 2: {
                    boolean productSettings = true;
                    while (productSettings) {
                        System.out.println("1=>Add Product. 2=>EditProductPrice. 3=>AddQuantityProduct. 4=>Remove Product. 5=>Back");
                        int productOption = scannerInt.nextInt();
                        switch (productOption) {
                            case 1: {
                                marketService.addProduct();
                                break;
                            }
                            case 2:
                                marketService.editPriceProduct();
                                break;
                            case 3:
                                marketService.addQuantityProduct();
                                break;
                            case 4:
                                marketService.removeProduct();
                                break;
                            case 5:
                                productSettings = false;
                                break;
                            default:
                                System.out.println(MessageHelper.WRONG_OPTION);
                        }
                    }
                    break;
                }
                case 3: {
                    userService.userList();
                    break;
                }
                case 4:
                    userService.sellingHistory();
                    break;
                case 5:
                    userService.sellingHistoryByUser(user);
                    break;
                case 6: {
                    System.out.println("Balance : " + user.getBalance());
                    break;
                }
                case 7: {
                    adminactive = false;
                    break;
                }
                default: {
                    System.out.println(MessageHelper.WRONG_OPTION);
                    break;
                }

            }
        }
    }

}
