package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        User user1 = new User();
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("DISGUESD", "TOAST", (byte) 30);
        user.saveUser("MIYOUNG", "KKATAMINA", (byte) 25);
        user.saveUser("LILY", "PICHU", (byte) 24);
        user.saveUser("SYKKUNO", "WHAT_IS_UP_GUYS", (byte) 32);
        user.getAllUsers();
        System.out.println(user.getAllUsers().toString());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
