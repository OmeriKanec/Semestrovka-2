public class Node {
    Node left;
    Node right;
    int level;
    int value;

    public Node(int value, int level, Node left, Node right) {
        this.value = value;
        this.level = level;
        this.left = left;
        this.right = right;
    }
    public Node(int value) {
        this.value = value;
        this.level = 1;
        this.left = null;
        this.right = null;
    }
    public boolean isLeaf(){
        return (left == null && right == null);
    }
}
