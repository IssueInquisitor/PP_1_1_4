package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private final Connection connection = Util.getConnection();

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists users " +
                    "(id int  not null auto_increment primary key," +
                    "name varchar(45) default null," +
                    "lastName varchar(45) default null," +
                    "age tinyint)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ppsm = connection
                .prepareStatement("insert into users(name, lastName, age) VALUES(?, ?, ?)")) {
            ppsm.setString(1, name);
            ppsm.setString(2, lastName);
            ppsm.setByte(3, age);
            ppsm.executeUpdate();
            System.out.println("User " + name + " add to table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement ppsm = connection
                .prepareStatement("delete from users where id = ?")) {
            ppsm.setLong(1, id);
            ppsm.executeUpdate();
            System.out.println("User " + id + " deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ppsm = connection
                .prepareStatement("select id, name, lastName, age from users")) {
            ResultSet resultSet = ppsm.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

