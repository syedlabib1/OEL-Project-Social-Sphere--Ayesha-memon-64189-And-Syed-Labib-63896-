package Socialsphere;

import java.util.*;

public class Post {
    String content;
    String timestamp;
    int likes;
    CustomList<String> comments;

    public Post(String content) {
        this.content = content;
        this.timestamp = new Date().toString();  // Current timestamp
        this.likes = 0;
        this.comments = new CustomList<>();
    }

    public void addLike() {
        likes++;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void display() {
        System.out.println(content + " [Posted at: " + timestamp + "] Likes: " + likes);
        System.out.println("Comments:");
        if (comments.size() == 0) {
            System.out.println("No comments yet.");
        } else {
            for (int i = 0; i < comments.size(); i++) {
                System.out.println("- " + comments.get(i));
            }
        }
    }
}
