public class AATree {
    Node root;
    int addRecCount = 0;
    int findRecCount = 0;
    int delRecCount = 0;

    public Node skew(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else if (node.left.level == node.level) {
            Node L = node.left;
            node.left = L.right;
            L.right = node;
            return L;
        } else {
            return node;
        }
    }

    public Node split(Node node) {
        if (node == null) {
            return null;
        } else if (node.right == null || node.right.right == null) {
            return node;
        } else if (node.level == node.right.right.level) {
            Node r = node.right;
            node.right = r.left;
            r.left = node;
            r.level = r.level + 1;
            return r;
        } else {
            return node;
        }
    }

    public int[] insert(int x){
        addRecCount = 0;
        long start = System.nanoTime();
        this.root = this.insert(this.root, x);
        long finish = System.nanoTime();
        long time = finish - start;
        int[] ar = new int[2];
        ar[0] = addRecCount;
        ar[1] = (int) time;
        return ar;
    }
    private Node insert(Node node, int x) {
        addRecCount += 1;
        if (node == null) {
            return new Node(x);
        }
        if (x < node.value) {
            node.left = insert(node.left, x);
        }
        else if (x > node.value) {
            node.right = insert(node.right, x);
        }

        node = skew(node);
        node = split(node);
        return node;
    }
    static Node minValueNode(Node node) {
        Node current = node;
        while (current != null && current.left != null)
            current = current.left;
        return current;
    }
    public int delete(int x){
        delRecCount = 0;
        this.root = this.delete(this.root, x);
        return delRecCount;
    }
    private Node delete(Node root, int x) {
        delRecCount += 1;
        if (root == null) {
            return root;
        }
        if (x < root.value) {
            root.left = delete(root.left, x);
        }
        else if (x > root.value) {
            root.right = delete(root.right, x);
        } else {
            if (root.left == null) {
                Node temp = root.right;
                return temp;
            }
            else if (root.right == null) {
                Node temp = root.left;
                return temp;
            }
            Node temp = minValueNode(root.right);
            root.value = temp.value;
            root.right = delete(root.right, temp.value);
        }
        root = decreaseLevel(root);
        root = skew(root);
        root.right = skew(root.right);
        if (root.right != null){
            root.right.right = skew(root.right.right);
        }
        root = split(root);
        root.right = split(root.right);
        return root;
    }
    public Node decreaseLevel(Node node) {
        int ll;
        if (node.left == null){
            ll = 0;
        }else {
            ll = node.left.level;
        }
        int rl;
        if (node.right == null){
            rl = 0;
        }else {
            rl = node.right.level;
        }
            int s = Math.min(ll, rl) + 1;
            if (s < node.level) {
                node.level = s;
                if (s < rl) {
                    node.right.level = s;
                }
            }
            return node;
    }
    public boolean contains(Node node, int x){
        findRecCount += 1;
        if (node.value == x){
            return true;
        }
        if (x < node.value){
            return contains(node.left, x);
        }
        if (x > node.value){
            return contains(node.right, x);
        }
        if (node == null){
            return false;
        }
        return false;
    }
}

