import service.CommentService;
import service.LikeService;
import service.PostService;
import service.UserService;

import java.util.Scanner;

public class Main {
//    new comment just for testing purposes

    static Scanner stringScanner = new Scanner(System.in);
    static UserService userService = new UserService();
    static PostService postService = new PostService();
    static LikeService likeService = new LikeService();
    static CommentService commentService = new CommentService();

    public static void main(String[] args) {
        while (true) {

            System.out.println("Choose the flow(user/post/like/comment/quit): ");
            String flowInput = stringScanner.nextLine();
            switch (flowInput.toLowerCase()) {
                case "user":
                    startUserFlow();
                    break;
                case "post":
                    startPostFlow();
                    break;
                case "like":
                    startLikeFlow();
                    break;
                case "comment":
                    startCommentFlow();
                    break;
                case "quit":
                case "q":
                    return;
                default:
                    System.out.println("Invalid flow!");
                    break;
            }
        }
    }

    public static void startUserFlow() {
        while (true) {
            System.out.println("\n\nCreate user - C/Read all users - RA/Read by user id - RBI\nUpdate user - U/Delete user - D/Exit flow - Q");
            String operationInput = chooseCrudOperation("RA/RBI/C/U/D/Q");
            switch (operationInput.toUpperCase()) {
                case "RA":
                    userService.readAll();
                    break;
                case "RBI":
                    userService.readById();
                    break;
                case "C":
                    userService.create();
                    break;
                case "U":
                    userService.update();
                    break;
                case "D":
                    userService.deleteById();
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid op");
            }
        }
    }

    public static void startPostFlow() {
        System.out.println("\n\nCreate post - C/Read all posts - RA/Read post by user id- RBUI\nUpdate post -U/Delete post - D/Quit - Q");
        String operationInput = chooseCrudOperation("RA/RBUI/C/U/D/Q");

        switch (operationInput.toUpperCase()) {
            case "RA":
                postService.readAll();
                break;
            case "RBUI":
                postService.readByUserId();
                break;
            case "C":
                postService.create();
                break;
            case "U":
                postService.update();
                break;
            case "D":
                postService.deleteById();
                break;
            case "Q":
                return;
            default:
                System.out.println("Invalid op");
        }
    }

    private static void startLikeFlow() {
        String operationInput = chooseCrudOperation("C/D/Q");
        switch (operationInput.toUpperCase()) {
            case "C":
                likeService.create();
                break;
            case "D":
                likeService.delete();
                break;
            case "Q":
                return;
            default:
                System.out.println("Invalid op");
                break;
        }
    }

    private static void startCommentFlow() {
        String operationInput = chooseCrudOperation("C/U/D/Q");
        switch (operationInput.toUpperCase()) {
            case "C":
                commentService.create();
                break;
            case "U":
                commentService.update();
                break;
            case "D":
                commentService.delete();
                break;
            case "Q":
                return;
            default:
                System.out.println("Invalid op");
                break;
        }
    }

    public static String chooseCrudOperation(String possibleOperations) {
        System.out.println("Insert wanted operation (" + possibleOperations + "): ");
        return stringScanner.nextLine();
    }

}
