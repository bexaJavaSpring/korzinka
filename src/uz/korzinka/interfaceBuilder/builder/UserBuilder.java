package uz.korzinka.interfaceBuilder.builder;

import uz.korzinka.helper.enums.UserRole;

@FunctionalInterface
public interface UserBuilder<T> {

    T create(Integer id, String username, String password, UserRole role, double balance);

}
