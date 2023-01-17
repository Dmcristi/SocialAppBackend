package repository;

import database.config.DatabaseConnector;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepository {

    public void create(User user) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String sqlQueryTemplate = String.format("INSERT INTO user_table (nume, email, varsta) VALUES ('%s', '%s', %d)",
                    user.getNume(), user.getEmail(), user.getVarsta());
            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("User created!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }
    }

    public ArrayList<User> readAll() {
        ArrayList<User> userArrayList = new ArrayList<>();
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM user_table");
            while (rs.next()) {
                userArrayList.add(new User(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("varsta")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
        return userArrayList;
    }

    public User readById(int userId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM user_table WHERE id = " + userId);
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("varsta")
                );
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {

        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String sqlQueryTemplate = String.format("UPDATE user_table SET nume='%s', email='%s', varsta=%d WHERE id=%d",
                    user.getNume(), user.getEmail(), user.getVarsta(), user.getId());
            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("User updated!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
    }

    public void delete(int userId) {

        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = updateStatement.executeUpdate("DELETE FROM user_table WHERE id = " + userId);
            if (affectedRows != 0) {
                System.out.println("User deleted!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
    }

}
