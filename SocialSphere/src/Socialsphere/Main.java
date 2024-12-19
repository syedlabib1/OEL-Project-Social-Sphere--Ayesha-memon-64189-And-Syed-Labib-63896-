package Socialsphere;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork socialNetwork = new SocialNetwork();

        while (true) {
            System.out.println("\n--- Social Network ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend");
            System.out.println("3. Create Post");
            System.out.println("4. Like Post");
            System.out.println("5. Comment on Post");
            System.out.println("6. Show Social Feed");
            System.out.println("7. Suggest Friends");
            System.out.println("8. Find Shortest Path");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter bio: ");
                    String bio = scanner.nextLine();
                    socialNetwork.addUser(username, bio);
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String user1 = scanner.nextLine();
                    System.out.print("Enter friend's username: ");
                    String friend1 = scanner.nextLine();
                    socialNetwork.addFriend(user1, friend1);
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    String user2 = scanner.nextLine();
                    System.out.print("Enter post content: ");
                    String content = scanner.nextLine();
                    socialNetwork.createPost(user2, content);
                    break;
                case 4:
                    System.out.print("Enter username: ");
                    String user3 = scanner.nextLine();
                    System.out.print("Enter post index to like: ");
                    int postIndexLike = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    socialNetwork.likePost(user3, postIndexLike);
                    break;
                case 5:
                    System.out.print("Enter username: ");
                    String user4 = scanner.nextLine();
                    System.out.print("Enter post index to comment on: ");
                    int postIndexComment = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter comment: ");
                    String comment = scanner.nextLine();
                    socialNetwork.commentOnPost(user4, postIndexComment, comment);
                    break;
                case 6:
                    System.out.print("Enter username to view feed: ");
                    String user5 = scanner.nextLine();
                    socialNetwork.showSocialFeed(user5);
                    break;
                case 7:
                    System.out.print("Enter username for friend suggestions: ");
                    String user6 = scanner.nextLine();
                    socialNetwork.suggestFriends(user6);
                    break;
                case 8:
                    System.out.print("Enter start username: ");
                    String startUser = scanner.nextLine();
                    System.out.print("Enter end username: ");
                    String endUser = scanner.nextLine();
                    socialNetwork.findShortestPath(startUser, endUser);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
