package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Petr", "Ivanov", (byte) 25);
        userService.saveUser("Oleg", "Lyutikov", (byte) 22);
        userService.saveUser("Soslan", "Dzits", (byte) 23);
        userService.saveUser("Ivan", "Olegov", (byte) 19);

        userService.removeUserById(1);
//
//        userService.getAllUsers();
//
//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();
    }
}
