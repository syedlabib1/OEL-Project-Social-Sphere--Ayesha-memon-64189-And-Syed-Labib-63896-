package Socialsphere;

import java.util.*;

public class User {
    String username;
    String bio;
    CustomList<Post> posts;
    AVLTree friends;

    public User(String username, String bio) {
        this.username = username;
        this.bio = bio;
        this.posts = new CustomList<>();
        this.friends = new AVLTree();  // Use AVL Tree to manage friends
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addFriend(String friendUsername) {
        friends.insert(friendUsername);
    }

    public boolean isFriend(String friendUsername) {
        return friends.contains(friendUsername);
    }

    public List<String> getFriends() {
        return friends.inorder();
    }
}
