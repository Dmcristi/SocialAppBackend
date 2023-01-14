package repository;

import database.config.DatabaseConnector;
import entity.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    public void create(Post post) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String sqlQueryTemplate = String.format("INSERT INTO post_table (mesaj, an, luna, zi, ora, minut , user_id) " +
                            "VALUES ('%s', %d, %d, %d, %d, %d, %d)",
                    post.getMesaj(),
                    post.getCreateAtDate().getYear(),
                    post.getCreateAtDate().getMonthValue(),
                    post.getCreateAtDate().getDayOfMonth(),
                    post.getCreateAtDate().getHour(),
                    post.getCreateAtDate().getMinute(),
                    post.getUserId());
            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("Post created!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
    }

    public List<Post> readAll() {
        ArrayList<Post> postList = new ArrayList<>();
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM post_table");
            while (rs.next()) {
                postList.add(new Post(
                        rs.getInt("id"),
                        rs.getString("mesaj"),
                        LocalDateTime.of(rs.getInt("an"),
                                rs.getInt("luna"),
                                rs.getInt("zi"),
                                rs.getInt("ora"),
                                rs.getInt("minut"),
                                0),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
        return postList;
    }

    public Post readById(int postId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM post_table WHERE id= " + postId);
            rs.next();
            return new Post(
                    rs.getInt("id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut"),
                            0),
                    rs.getInt("user_id")
            );
        } catch (SQLException e) {
            System.out.println("Invalid post id: " + postId);
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Post> readByUserId(int userId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        ArrayList<Post> postList = new ArrayList<>();

        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM post_table WHERE user_id = " + userId);
            while (rs.next()) {
                postList.add(new Post(
                        rs.getInt("id"),
                        rs.getString("mesaj"),
                        LocalDateTime.of(rs.getInt("an"),
                                rs.getInt("luna"),
                                rs.getInt("zi"),
                                rs.getInt("ora"),
                                rs.getInt("minut"),
                                0),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
        return postList;
    }

    public void update(Post post) {

        LocalDateTime localDateTime = post.getCreateAtDate();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();

        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String sqlQueryTemplate = String.format("UPDATE post_table SET mesaj='%s', an=%d, luna=%d, zi=%d, ora=%d, minut=%d WHERE id=%d",
                    post.getMesaj(), year, month, day, hour, minute, post.getId());
            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("Post updated!");
            }
        } catch (
                SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
    }

    public void deleteByPostId(int postId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();

        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = updateStatement.executeUpdate("DELETE FROM post_table WHERE id = " + postId);
            if (affectedRows != 0) {
                System.out.println("Post deleted!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query");
            e.printStackTrace();
        }
    }
}
