package com.company;

class Index {
    int index = 0;
}

class BST {

    public static Index index = new Index();

    // A recursive function to construct BST from pre[]. preIndex is used
    // to keep track of index in pre[].
    public static Node constructTreeUtil(int pre[], int index, int key,
                           int min, int max, int size) {

        // Base case
        if (index >= size) {
            return null;
        }

        Node root = null;

        // If current element of pre[] is in range, then
        // only it is part of current subtree
        if (key > min && key < max) {

            // Allocate memory for root of this
            // subtree and increment *preIndex
            root = new Node(key);
            index = index + 1;

            if (index < size) {

                // Construct the subtree under root
                // All nodes which are in range {min .. key}
                // will go in left subtree, and first such
                // node will be root of left subtree.
                root.left = constructTreeUtil(pre, index,
                        pre[index], min, key, size);

                // All nodes which are in range {key..max}
                // will go in right subtree, and first such
                // node will be root of right subtree.
                root.right = constructTreeUtil(pre, index,
                        pre[index], key, max, size);
            }
        }

        return root;
    }

    // The main function to construct BST from given preorder traversal.
    // This function mainly uses constructTreeUtil()
    public static Node constructTree(int pre[], int size) {
//        int preIndex = 0;
        return constructTreeUtil(pre, 0, pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
    }

    // A utility function to print inorder traversal of a Binary Tree
    public static void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        BST tree = new BST();
        int pre[] = new int[]{10, 5, 1, 7, 40, 50};
        int size = pre.length;
        Node root = tree.constructTree(pre, size);
        System.out.println("Inorder traversal of the constructed tree is ");
        tree.printInorder(root);
    }
}

// This code has been contributed by Mayank Jaiswal
