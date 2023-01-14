package service;

import entity.Comment;
import entity.Post;
import entity.User;
import repository.CommentRepository;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostService {

    private final Scanner intScanner = new Scanner(System.in);
    private final Scanner stringScanner = new Scanner(System.in);

    private final PostRepository postRepository = new PostRepository();
    private final UserRepository userRepository = new UserRepository();
    private final LikeRepository likeRepository = new LikeRepository();
    private final CommentRepository commentRepository = new CommentRepository();

    public void create() {
        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();
        User user = userRepository.readById(userId);
        if (user != null) {
            Post post = new Post();
            System.out.println("Enter message: ");
            post.setMesaj(stringScanner.nextLine());
            post.setCreateAtDate(LocalDateTime.now());
            post.setUserId(user.getId());

            postRepository.create(post);
        } else {
            System.out.println("Invalid user id: " + userId);
        }
    }

    public void readAll() {
        List<Post> postList = postRepository.readAll();
        if (postList != null) {
            for (Post post : postList) {
                post.setLikes(likeRepository.getPostLikes(post.getId()));
                post.setComments(commentRepository.getPostComments(post.getId()));
            }
            postList.forEach(System.out::println);
        }else {
            System.out.println("No posts in the database");
        }
    }

    public void readByUserId() {
        System.out.println("Enter user id: ");
        int userId = intScanner.nextInt();
        User user = userRepository.readById(userId);
        if (user != null) {
            postRepository.readByUserId(userId).forEach(post -> {
                post.setLikes(likeRepository.getPostLikes(post.getId()));
                post.setComments(commentRepository.getPostComments(post.getId()));
                System.out.println(post);
            });
        } else {
            System.out.println("Invalid user id: " + userId);
        }
    }

    public void update() {
        System.out.println("Enter post id: ");
        int postId = intScanner.nextInt();

        Post post = postRepository.readById(postId);

        if (post != null) {
            System.out.println("Edit message?Y/N: ");
            String modifyMessageStatus = stringScanner.nextLine();
            if (modifyMessageStatus.equalsIgnoreCase("y")) {
                System.out.println("Enter message: ");
                post.setMesaj(stringScanner.nextLine());
            }
            postRepository.update(post);
        } else {
            System.out.println("Invalid post id: " + postId);
        }
    }

    public void deleteById() {
        System.out.println("Enter post id: ");
        int postId = intScanner.nextInt();
        Post post = postRepository.readById(postId);
        if (post != null) {
            ArrayList<Comment> comments = commentRepository.getPostComments(postId);
            ArrayList<Integer> likes = likeRepository.getPostLikes(postId);
            for (Comment comment: comments) {
                commentRepository.deleteByCommentId(comment.getId());
            }
            for (Integer like: likes) {
                likeRepository.delete(like, postId);
            }
            postRepository.deleteByPostId(postId);
        } else {
            System.out.println("Invalid post id: " + postId);
        }
    }

}
