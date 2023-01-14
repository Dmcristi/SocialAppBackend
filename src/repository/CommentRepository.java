package repository;

import database.config.DatabaseConnector;
import entity.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentRepository {

    public ArrayList<Comment> getPostComments(int postId) {
        ArrayList<Comment> comments = new ArrayList<>();

        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM comment_table WHERE post_id= " + postId);
            while (rs.next()) {
                comments.add(new Comment(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("mesaj"),
                        LocalDateTime.of(rs.getInt("an"),
                                rs.getInt("luna"),
                                rs.getInt("zi"),
                                rs.getInt("ora"),
                                rs.getInt("minut"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public void create(Comment comment, int postId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String sqlQueryTemplate = String.format(
                    "INSERT INTO comment_table(user_id, post_id, mesaj, an, luna, zi, ora, minut) VALUES(%d, %d, '%s', %d, %d, %d, %d, %d)",
                    comment.getUserId(),
                    postId,
                    comment.getMesaj(),
                    comment.getCreateAtDate().getYear(),
                    comment.getCreateAtDate().getMonthValue(),
                    comment.getCreateAtDate().getDayOfMonth(),
                    comment.getCreateAtDate().getHour(),
                    comment.getCreateAtDate().getMinute()
            );

            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("Comment added!");
            } else {
                System.out.println("Comment not added");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }

    }

    public Comment readByCommentId(int commentId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement readStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = readStatement.executeQuery("SELECT * FROM comment_table WHERE id= " + commentId);
            rs.next();
            return new Comment(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut"))
            );
        } catch (SQLException e) {
            System.out.println("Comment with id " + commentId + " is not valid");
            e.printStackTrace();
            return null;
        }
    }

    public void updateComment(Comment comment) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            String sqlQueryTemplate = String.format("UPDATE comment_table SET user_id=%d, mesaj='%s', an=%d, luna=%d, zi=%d, ora=%d, minut=%d WHERE id=%d",
                    comment.getUserId(),
                    comment.getMesaj(),
                    comment.getCreateAtDate().getYear(),
                    comment.getCreateAtDate().getMonthValue(),
                    comment.getCreateAtDate().getDayOfMonth(),
                    comment.getCreateAtDate().getHour(),
                    comment.getCreateAtDate().getMinute(),
                    comment.getId()
            );
            int affectedRows = updateStatement.executeUpdate(sqlQueryTemplate);
            if (affectedRows > 0) {
                System.out.println("Comment updated!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }
    }

    public void deleteByCommentId(int commentId) {
        Connection connection = DatabaseConnector.getInstance().getConnection();
        try {
            Statement updateStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = updateStatement.executeUpdate("DELETE FROM comment_table WHERE id=" + commentId);
            if (affectedRows > 0) {
                System.out.println("Comment deleted!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid query!");
            e.printStackTrace();
        }
    }

}
