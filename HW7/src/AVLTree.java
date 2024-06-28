public class AVLTree {
    private class Node {
        Stock stock;
        int height;
        Node left, right;

        // Node constructor
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1; // Initial height of a node is 1
        }
    }

    private Node root; // Root node of the AVL tree

    // Method to get the height of a node
    private int height(Node n) {
        // If node is null, its height is 0
        // Otherwise, return the height of the node
        return n == null ? 0 : n.height;
    }

    // Method to get the balance factor of a node
    private int getBalance(Node n) {
        // If node is null, its balance factor is 0
        // Otherwise, calculate the balance factor as the difference between the heights of the left and right subtrees
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // Method to perform right rotation on a subtree rooted with y
    private Node rightRotate(Node y) {
        // Perform rotation
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        // Return new root
        return x;
    }

    // Method to perform left rotation on a subtree rooted with x
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        // Return new root
        return y;
    }

    // Method to insert a key into the AVL tree
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    // Recursive method to insert a key into the AVL tree
    private Node insert(Node node, Stock stock) {
        // If the tree is empty, return a new node
        if (node == null)
            return new Node(stock);

        // Otherwise, recur down the tree
        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0)
            node.left = insert(node.left, stock);
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0)
            node.right = insert(node.right, stock);
        else
            // Equal keys are not allowed in AVL tree
            return node;

        // Update height of the ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether this node became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Method to delete a key from the AVL tree
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    // Recursive method to delete a key from the AVL tree
    private Node delete(Node root, String symbol) {
        // Standard BST delete
        if (root == null)
            return root;

        // If the key to be deleted is smaller than the root's key, then it lies in left subtree
        if (symbol.compareTo(root.stock.getSymbol()) < 0)
            root.left = delete(root.left, symbol);

            // If the key to be deleted is greater than the root's key, then it lies in right subtree
        else if (symbol.compareTo(root.stock.getSymbol()) > 0)
            root.right = delete(root.right, symbol);

            // If key is same as root's key, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    // One child case
                    root = temp;
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.stock = temp.stock;

                // Delete the inorder successor
                root.right = delete(root.right, temp.stock.getSymbol());
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // Update height of the current node
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Get the balance factor of this node (to check whether this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Method to get the node with minimum value
    private Node minValueNode(Node node) {
        Node current = node;
        // Loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Method to search for a key in the AVL tree
    public Stock search(String symbol) {
        return search(root, symbol);
    }

    // Recursive method to search for a key in the AVL tree
    private Stock search(Node node, String symbol) {
        // Base Cases: root is null or key is present at root
        if (node == null || symbol.equals(node.stock.getSymbol()))
            return node == null ? null : node.stock;

        // Key is greater than root's key
        if (symbol.compareTo(node.stock.getSymbol()) > 0)
            return search(node.right, symbol);

        // Key is smaller than root's key
        return search(node.left, symbol);
    }

    // Method to perform inorder traversal of the AVL tree
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    // Recursive method to perform inorder traversal of the AVL tree
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.stock + " ");
            inorder(node.right);
        }
    }

    // Method to perform preorder traversal of the AVL tree
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    // Recursive method to perform preorder traversal of the AVL tree
    private void preorder(Node node) {
        if (node != null) {
            System.out.print(node.stock + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Method to perform postorder traversal of the AVL tree
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    // Recursive method to perform postorder traversal of the AVL tree
    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.stock + " ");
        }
    }

    // Method to get the size of the AVL tree
    public int size() {
        return size(root);
    }

    // Recursive method to get the size of the AVL tree
    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }
}