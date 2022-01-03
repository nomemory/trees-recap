package net.andreinc.trees;

import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {

    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
        }
    }

    Node<T> root;

    /**
     Given a binary tree, return true if a node
     with the target data is found in the tree. Recurs
     down the tree, chooses the left or right
     branch by comparing the target to each node.
    */

    protected static <T extends Comparable<T>> boolean lookup(Node<T> crtNode, T data) {
        if (crtNode==null) {
            return false;
        }
        int cmpRes = crtNode.data.compareTo(data);
        if (cmpRes==0) {
            return true;
        } else if (cmpRes<0) {
            return lookup(crtNode.left, data);
        }
        else {
            return lookup(crtNode.right, data);
        }
    }

    public boolean lookup(T data) {
        return BinaryTree.lookup(root, data);
    }

    /**
     * Inserts new element in the tree
     */
    protected static <T extends Comparable<T>> Node<T> insert(Node<T> crtNode, T data) {
        if (crtNode==null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(crtNode.data);
        if (cmp<=0) {
            crtNode.left = insert(crtNode.left, data);
        }
        else {
            crtNode.right = insert(crtNode.right, data);
        }
        return crtNode;
    }


    public void insert(T data) {
        if (root==null) {
            root = new Node<>(data);
            return;
        }
        BinaryTree.insert(root, data);
    }

    public void insertAll(Iterable<T> elements) {
        for(T element : elements) {
            this.insert(element);
        }
    }

    /**
     * Given a binary tree, count the number of nodes in the tree.
     */
    protected static <T extends Comparable<T>> long size(Node<T> crtNode) {
        if (crtNode==null) {
            return 0l;
        }
        return 1l + size(crtNode.left) + size(crtNode.right);
    }

    public long size() {
        return BinaryTree.size(root);
    }

    /**
     Returns the max root-to-leaf depth of the tree.
     Uses a recursive helper that recurs down to find
     the max depth.
     */

    protected static <T extends Comparable<T>> long maxDepth(Node<T> crtNode) {
        if (crtNode==null) {
            return 0;
        }
        long leftMaxDepth = maxDepth(crtNode.left);
        long rightMaxDepth = maxDepth(crtNode.right);

        return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    }

    public long maxDepth() {
        return BinaryTree.maxDepth(root);
    }

    /**
     * In order traversal
     */

    protected static <T extends Comparable<T>> void traverseInOrder(Node<T> crtNode, Consumer<T> consumer) {
        if (crtNode == null) {
            return;
        }
        traverseInOrder(crtNode.left, consumer);
        consumer.accept(crtNode.data);
        traverseInOrder(crtNode.right, consumer);
    }

    public void traverseInOrder(Consumer<T> consumer) {
        BinaryTree.traverseInOrder(root, consumer);
    }

    /**
     * Pre-Order traversal
     */

    protected static <T extends Comparable<T>> void traversePreOrder(Node<T> crtNode, Consumer<T> consumer) {
        if (crtNode==null) {
            return;
        }
        consumer.accept(crtNode.data);
        traversePreOrder(crtNode.left, consumer);
        traversePreOrder(crtNode.right, consumer);
    }

    public void traversePreOrder(Consumer<T> consumer) {
        BinaryTree.traversePreOrder(root, consumer);
    }

    /**
     * Post-Order traversal
     */

    protected static <T extends Comparable<T>> void traversePostOrder(Node<T> crtNode, Consumer<T> consumer) {
        if (crtNode==null) {
            return;
        }
        traversePostOrder(crtNode.left, consumer);
        traversePostOrder(crtNode.right, consumer);
        consumer.accept(crtNode.data);
    }

    public void traversePostOrder(Consumer<T> consumer) {
        BinaryTree.traversePostOrder(root, consumer);
    }

    /**
     * Finds the minimum value in a tree
     */
    public T findMin(Node<T> node) {
        Node<T> crt = node;
        while(crt.left!=null) {
            crt=crt.left;
        }
        return crt.data;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insertAll(List.of(25,15,50,10,22,35,70,4,12,18,24,31,44,66,90));
        binaryTree.traverseInOrder(e -> System.out.print(e + " "));
        System.out.println("--");
        binaryTree.traversePostOrder(e -> System.out.print(e + " "));
        System.out.println("--");
        binaryTree.traversePreOrder(e -> System.out.print(e + " "));
    }
}
