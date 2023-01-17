package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
private Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (
                Statement statement = connection.createStatement();
        ) {
            String sql = "CREATE TABLE mydbtest.`users`" +
                    "(`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`NAME` VARCHAR(50)," +
                    "`LAST_NAME` VARCHAR(50)," +
                    "`AGE` INTEGER," +
                    "PRIMARY KEY (`ID`))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Таблица Users уже существует");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sqlDrop = "DROP TABLE mydbtest.`users`";
            statement.executeUpdate(sqlDrop);
        } catch (SQLException e) {
            System.out.println("Таблица Users не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO mydbtest.users (name, last_Name, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM mydbtest.users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> al = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM mydbtest.users")) {
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("ID"));
                user.setName(res.getString("NAME"));
                user.setLastName(res.getString("LAST_NAME"));
                user.setAge(res.getByte("AGE"));
                al.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return al;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM mydbtest.users")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
