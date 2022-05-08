/**
 * @author Alan Huang
 *         Node class for the Binary Search Tree
 */
public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    /**
     * Constructor for the Node class
     * 
     * @param data
     */
    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * @return T return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return Node<T> return the left
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * @return Node<T> return the right
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

}
