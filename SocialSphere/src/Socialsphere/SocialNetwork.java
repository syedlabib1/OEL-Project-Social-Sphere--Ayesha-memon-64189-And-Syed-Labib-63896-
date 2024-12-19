package Socialsphere;

import java.util.*;

public class SocialNetwork {
    Map<String, User> users;

    public SocialNetwork() {
        users = new HashMap<>();
    }

    public void addUser(String username, String bio) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, bio));
            System.out.println(username + " has been added to the network.");
        } else {
            System.out.println("User already exists.");
        }
    }

    public void addFriend(String username, String friendUsername) {
        User user = users.get(username);
        User friend = users.get(friendUsername);
        if (user != null && friend != null && !user.isFriend(friendUsername)) {
            user.addFriend(friendUsername);
            friend.addFriend(username);
            System.out.println(friendUsername + " added as a friend to " + username);
        } else {
            System.out.println("Invalid users or already friends.");
        }
    }

    public void createPost(String username, String content) {
        User user = users.get(username);
        if (user != null) {
            Post newPost = new Post(content);
            user.addPost(newPost);
            System.out.println(username + " posted: " + content);
        } else {
            System.out.println("User does not exist.");
        }
    }

    public void likePost(String username, int postIndex) {
        User user = users.get(username);
        if (user != null && postIndex >= 0 && postIndex < user.posts.size()) {
            Post post = user.posts.get(postIndex);
            post.addLike();
            System.out.println(username + " liked a post.");
        } else {
            System.out.println("Invalid post index.");
        }
    }

    public void commentOnPost(String username, int postIndex, String comment) {
        User user = users.get(username);
        if (user != null && postIndex >= 0 && postIndex < user.posts.size()) {
            Post post = user.posts.get(postIndex);
            post.addComment(comment);
            System.out.println(username + " commented: " + comment);
        } else {
            System.out.println("Invalid post index.");
        }
    }

    public void showSocialFeed(String username) {
        User user = users.get(username);
        if (user != null) {
            List<Post> feed = new ArrayList<>();
            for (String friendUsername : user.getFriends()) {
                User friend = users.get(friendUsername);
                if (friend != null) {
                    // Manually adding posts from friends to the feed
                    for (int i = 0; i < friend.posts.size(); i++) {
                        feed.add(friend.posts.get(i));
                    }
                }
            }
            // Sorting the posts by timestamp (latest first)
            feed.sort((p1, p2) -> p2.timestamp.compareTo(p1.timestamp));
            System.out.println(username + "'s Social Feed:");
            if (feed.isEmpty()) {
                System.out.println("No posts in the feed.");
            } else {
                for (Post post : feed) {
                    post.display();
                    System.out.println("-----------------------------");
                }
            }
        }
    }

    public void suggestFriends(String username) {
        User user = users.get(username);
        if (user != null) {
            Map<String, Integer> mutualFriends = new HashMap<>();
            for (String friendUsername : user.getFriends()) {
                User friend = users.get(friendUsername);
                if (friend != null) {
                    for (String friendOfFriend : friend.getFriends()) {
                        if (!friendOfFriend.equals(username) && !user.isFriend(friendOfFriend)) {
                            mutualFriends.put(friendOfFriend, mutualFriends.getOrDefault(friendOfFriend, 0) + 1);
                        }
                    }
                }
            }

            List<Map.Entry<String, Integer>> suggestions = new ArrayList<>(mutualFriends.entrySet());
            suggestions.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

            System.out.println(username + "'s Friend Suggestions:");
            for (Map.Entry<String, Integer> entry : suggestions) {
                System.out.println(entry.getKey() + " (Mutual friends: " + entry.getValue() + ")");
            }
        }
    }

    // Dijkstra's Shortest Path Implementation for User Connections (Friendships)
    public void findShortestPath(String startUser, String endUser) {
        User start = users.get(startUser);
        User end = users.get(endUser);

        if (start == null || end == null) {
            System.out.println("Invalid users.");
            return;
        }

        // Map each user to an index in the graph
        List<String> allUsers = new ArrayList<>(users.keySet());
        int n = allUsers.size();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[allUsers.indexOf(startUser)] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> distances[i]));
        pq.add(allUsers.indexOf(startUser));

        while (!pq.isEmpty()) {
            int u = pq.poll();

            for (String friend : users.get(allUsers.get(u)).getFriends()) {
                int v = allUsers.indexOf(friend);
                int weight = 1; // Each friendship is a unit distance

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(v);
                }
            }
        }

        int shortestDistance = distances[allUsers.indexOf(endUser)];
        if (shortestDistance == Integer.MAX_VALUE) {
            System.out.println("No path found between " + startUser + " and " + endUser);
        } else {
            System.out.println("Shortest path between " + startUser + " and " + endUser + " is " + shortestDistance + " steps.");
        }
    }
}
