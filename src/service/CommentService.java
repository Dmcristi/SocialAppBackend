package service;

import entity.Comment;
import entity.Post;
import entity.User;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CommentService {

    private final Scanner intScanner = new Scanner(System.in);
    private final Scanner stringScanner = new Scanner(System.in);
    private final PostRepository postRepository = new PostRepository();
    private final CommentRepository commentRepository = new CommentRepository();
    private final UserRepository userRepository = new UserRepository();

    public void create() {
        Comment comment = new Comment();
        System.out.println("Enter post id: ");
        int postId = intScanner.nextInt();
        Post post = postRepository.readById(postId);
        if (post != null) {
            System.out.println("Enter user id: ");
            int userId = intScanner.nextInt();
            User user = userRepository.readById(userId);
            if (user != null) {
                System.out.println("Enter your message: ");
                String messageInput = stringScanner.nextLine();
                comment.setUserId(userId);
                comment.setMesaj(messageInput);
                comment.setCreateAtDate(LocalDateTime.now());
                commentRepository.create(comment, postId);
            } else {
                System.out.println("User with id + " + userId + " is not valid");
            }
        } else {
            System.out.println("Post with id + " + postId + " is not valid");
        }
    }

    public void update() {

        System.out.println("Enter comment id: ");
        int commentId = intScanner.nextInt();
        Comment comment = commentRepository.readByCommentId(commentId);
        if (comment != null) {
            System.out.println("Edit message?(Y/N): ");
            String input = stringScanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                System.out.println("Enter your message: ");
                comment.setMesaj(stringScanner.nextLine());
                commentRepository.updateComment(comment);
            } else {
                System.out.println("Message edit canceled!");
            }
        } else {
            System.out.println("Comment with id + " + commentId + " is not valid");
        }
    }

    public void delete() {

        System.out.println("Enter comment id: ");
        int commentId = intScanner.nextInt();
        Comment comment = commentRepository.readByCommentId(commentId);
        if (comment != null) {
            commentRepository.deleteByCommentId(commentId);
        } else {
            System.out.println("Comment with id + " + commentId + " is not valid");
        }
    }

}
