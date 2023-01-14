package repository;

import database.config.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LikeRepository {

    public ArrayList<Integer> getPostLikes(int postId) {

        ArrayList<Integer> usersIds = new ArrayList<>();
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT user_id FROM like_table WHERE post_id= " + postId);
            while (rs.next()) {
                usersIds.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }

        return usersIds;
    }

    public void create(int userId, int postId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();

        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = updateStatement.executeUpdate(
                    String.format("INSERT INTO like_table(user_id, post_id) VALUES(%d, %d)", userId, postId));
            if (affectedRows > 0) {
                System.out.println("Liked!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }
    }

    public void delete(int userId, int postId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();

        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = updateStatement.executeUpdate(
                    String.format("DELETE FROM like_table WHERE user_id=%d AND post_id=%d", userId, postId));
            if (affectedRows > 0) {
                System.out.println("Unliked!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }
    }
}
