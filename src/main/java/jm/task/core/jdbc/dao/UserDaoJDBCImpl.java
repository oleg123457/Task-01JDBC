package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.exception.internal.StandardSQLExceptionConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """ 
        CREATE TABLE IF NOT EXISTS userTable(
        id SERIAL PRIMARY KEY ,
        name VARCHAR(128)NOT NULL,
        last_name VARCHAR(128)NOT NULL,
        age INT NOT NULL)
""";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS userTable";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO userTable (name, last_name, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        String sql = """
                DELETE FROM userTable WHERE id =?;
                """;
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = """
                SELECT * FROM userTable
                """;
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }return userList;
    }

    public void cleanUsersTable() {
        String sql = """
             TRUNCATE TABLE userTable
             """;
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}