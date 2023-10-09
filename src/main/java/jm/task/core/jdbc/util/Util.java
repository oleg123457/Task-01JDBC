package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static void main(String[] args) {
        Connection connection = Util.getConnection();
    }
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "Lo10122003";


    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }



}
