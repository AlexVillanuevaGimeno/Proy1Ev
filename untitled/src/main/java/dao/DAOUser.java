package dao;

import connection.MotorSQL;
import beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOUser {

    private MotorSQL motorSQL;

    private final String tableName = "usuario";
    private final String SQL_FINDALL = "SELECT * FROM " + tableName + " WHERE 1=1";

    public DAOUser(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public ArrayList<User> findAll(User user){
        String sql = "";
        sql = SQL_FINDALL;
        sql += " AND username = '" + user.getUsername() + "' AND password = '" + user.getPassword()+'\'';
        System.out.println(sql);
        ArrayList<User> usersList = new ArrayList<>();
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);
        try {
            System.out.println("TRY findall usuario");
            while(resultSet.next()){
                User userAux = new User();
                userAux.setIdUser(resultSet.getInt(1));
                System.out.println("resultSet.getInt(1)");
                System.out.println(resultSet.getInt(1));
                userAux.setUsername(resultSet.getString(7));
                System.out.println("resultSet.getString(7)");
                System.out.println(resultSet.getString(7));
                userAux.setPassword(resultSet.getString(8));
                System.out.println("resultSet.getString(8)");
                System.out.println(resultSet.getString(8));
                usersList.add(userAux);
                System.out.println(userAux.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        System.out.println("LISTA USERS: " + usersList);
        return usersList;
    }

}
