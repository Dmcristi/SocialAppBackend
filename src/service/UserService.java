package service;

import entity.Post;
import entity.User;
import exceptions.UserException;
import repository.CommentRepository;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final PostRepository postRepository = new PostRepository();
    private final CommentRepository commentRepository = new CommentRepository();
    private final LikeRepository likeRepository = new LikeRepository();

    private final Scanner intScanner = new Scanner(System.in);
    private final Scanner stringScanner = new Scanner(System.in);

    public void create() {
        User user = new User();
        System.out.println("Enter name: ");
        user.setNume(stringScanner.nextLine());
        System.out.println("Enter email: ");
        user.setEmail(stringScanner.nextLine());
        System.out.println("Enter age: ");
        user.setVarsta(intScanner.nextInt());
        userRepository.create(user);
    }

    public void readAll() {
        ArrayList<User> users = userRepository.readAll();
        if (!users.isEmpty()) {
            for (User user : users) {
                user.setPostsList(postRepository.readByUserId(user.getId()));
                for (Post post : user.getPostsList()) {
                    post.setLikes(likeRepository.getPostLikes(post.getId()));
                    post.setComments(commentRepository.getPostComments(post.getId()));
                }
            }
            users.forEach(System.out::println);
        } else {
            throw new UserException("There are no users in the database!");
        }
    }

    public void readById() {
        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();
        User user = userRepository.readById(userId);
        if (user != null) {
            user.setPostsList(postRepository.readByUserId(userId));
            for (Post post : user.getPostsList()) {
                post.setLikes(likeRepository.getPostLikes(post.getId()));
                post.setComments(commentRepository.getPostComments(post.getId()));
            }
            System.out.println(user);
        } else {
//            throw new UserException("Invalid user id: " + userId);
            System.out.println("Invalid user id: " + userId);
        }
    }

    public void update() {
        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();
        User user = userRepository.readById(userId);
        if (user == null) {
            System.out.println("Invalid user id: " + userId);
            return;
        }
        user.setNume(returnValueIfModified("nume", user));
        user.setEmail(returnValueIfModified("email", user));
        user.setVarsta(Integer.parseInt(returnValueIfModified("varsta", user)));
        userRepository.update(user);
    }


    public void deleteById() {
        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();
        if (userRepository.readById(userId) == null) {
            System.out.println("Invalid user id: " + userId);
        } else {
            ArrayList<Post> posts = postRepository.readByUserId(userId);
            for (Post post : posts) {
                postRepository.deleteByPostId(post.getId());
            }
            userRepository.delete(userId);
        }
    }

    public String returnValueIfModified(String columnName, User user) {
        System.out.println("Edit " + columnName + "?Y/N: ");
        String response = stringScanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            System.out.println("Enter " + columnName);
            return stringScanner.nextLine();
        } else {
            switch (columnName) {
                case "nume":
                    return user.getNume();
                case "email":
                    return user.getEmail();
                case "varsta":
                    return String.valueOf(user.getVarsta());
                default:
                    return null;
            }
        }
    }

}
