package Socialsphere;

import java.util.*;

public class AVLTree {
    private class Node {
        String username;
        Node left, right;
        int height;

        Node(String username) {
            this.username = username;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    public void insert(String username) {
        root = insert(root, username);
    }

    private Node insert(Node node, String username) {
        if (node == null) {
            return new Node(username);
        }

        if (username.compareTo(node.username) < 0) {
            node.left = insert(node.left, username);
        } else if (username.compareTo(node.username) > 0) {
            node.right = insert(node.right, username);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1 && username.compareTo(node.left.username) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && username.compareTo(node.right.username) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && username.compareTo(node.left.username) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && username.compareTo(node.right.username) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    public boolean contains(String username) {
        return contains(root, username);
    }

    private boolean contains(Node node, String username) {
        if (node == null) {
            return false;
        }

        if (node.username.equals(username)) {
            return true;
        } else if (username.compareTo(node.username) < 0) {
            return contains(node.left, username);
        } else {
            return contains(node.right, username);
        }
    }

    public List<String> inorder() {
        List<String> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<String> result) {
        if (node != null) {
            inorder(node.left, result);
            result.add(node.username);
            inorder(node.right, result);
        }
    }
}
