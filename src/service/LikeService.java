package service;

import entity.Post;
import entity.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class LikeService {

    private final UserRepository userRepository = new UserRepository();
    private final PostRepository postRepository = new PostRepository();
    private final LikeRepository likeRepository = new LikeRepository();

    private final Scanner intScanner = new Scanner(System.in);

    public void create() {
        System.out.println("Enter post id: ");
        int postId = intScanner.nextInt();

        ArrayList<Integer> userIdsWhoLiked = likeRepository.getPostLikes(postId);

        Post post = postRepository.readById(postId);
        if (post != null) {
            System.out.println("Enter user id: ");
            int userId = intScanner.nextInt();

            if(userIdsWhoLiked.contains(userId)){
                System.out.println(userId + " already liked this post!");
                return;
            }
            User user = userRepository.readById(userId);
            if (user != null) {
                likeRepository.create(userId, postId);
            } else {
                System.out.println("Invalid user id:  " + userId);
            }
        } else {
            System.out.println("Invalid post id: " + postId);
        }
    }

    public void delete() {
        System.out.println("Enter post id: ");
        int postId = intScanner.nextInt();
        Post post = postRepository.readById(postId);
        if (post != null) {
            System.out.println("Enter user id: ");
            int userId = intScanner.nextInt();
            User user = userRepository.readById(userId);
            if (user != null) {
                likeRepository.delete(userId, postId);
            } else {
                System.out.println("Invalid user id:  " + userId);
            }
        } else {
            System.out.println("Invalid post id: " + postId);
        }
    }
}
