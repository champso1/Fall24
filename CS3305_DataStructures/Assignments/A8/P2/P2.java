// Name: Casey Hampson
// Class: CS 3305/W01
// Term: Fall 2024
// Instructor: Sharon Perry
// Assignment: 8-Part-2-AVL


import java.util.ArrayList;
import java.util.Random;


public class P2 {
    private static Random random;

    // we traverse through the tree to check that each node has that 
    // its left child's value is less and its right child's value is greater
    // than its own value
    // then we recursively go through the left and right subtrees
    // doing the same thing until we reach a null value which returns true
    // since a null value is ``okay'' in this sense
    private static boolean IsBinaryTree(BST.TreeNode<Integer> node) {
        if (node == null) return true;
        // this is a bit complex looking but it just considers null children
        boolean check_current = 
                (node.left == null ? true : node.left.element < node.element)
            &&  (node.right == null ? true : node.right.element > node.element);

        return check_current && IsBinaryTree(node.left) && IsBinaryTree(node.right);
    }
    public static boolean IsBinaryTree(AVLTree<Integer> tree) {
        BST.TreeNode<Integer> root = (AVLTree.AVLTreeNode<Integer>)tree.getRoot();
        return IsBinaryTree(root);
    }


    // this time we go through and compute the balance factors for each node
    // and ensure that its absolute value is less than 2
    private static boolean IsBalanced(BST.TreeNode<Integer> _node) {
        if (_node == null) return true;

        // polymorphism to grab the AVLTreeNode-specific height
        AVLTree.AVLTreeNode<Integer> node = (AVLTree.AVLTreeNode<Integer>)_node;
        
        int left_height = node.left == null ? 
            0 : ((AVLTree.AVLTreeNode<Integer>)node.left).height;
        int right_height = node.right == null ?
            0 : ((AVLTree.AVLTreeNode<Integer>)node.right).height;
        boolean check_current = Math.abs(right_height - left_height) < 2;
        return check_current && IsBalanced(_node.left) && IsBalanced(_node.right);
    }
    public static boolean IsBalanced(AVLTree<Integer> tree) {
        BST.TreeNode<Integer> root = (AVLTree.AVLTreeNode<Integer>)tree.getRoot();
        return IsBalanced(root);
    }



    public static void TestAVLTree(AVLTree<Integer> tree) {
        // just print the tree's contents for completeness
        System.out.printf("Inorder traversal of the tree's contents:\n");
        tree.inorder();
        System.out.println();

        
        boolean is_bst = IsBinaryTree(tree);
        System.out.printf("Binary Search Tree? %s\n", is_bst ? "Yes!" : "No");

        boolean is_balanced = IsBalanced(tree);
        System.out.printf("Balanced? %s\n", is_balanced ? "Yes!" : "No");

        System.out.printf("Is an AVL tree? %s\n\n", (is_bst && is_balanced) ? "Yes!" : "No");
    }


    public static void main(String[] args) {
        random = new Random();

        // create 5 arrays of 10-20 integers ranging from 1-100
        ArrayList<Integer[]> arrs = new ArrayList<>();

        for (int i=0; i<5; i++) {
            int arr_len = random.nextInt(10, 20);
            Integer arr[] = new Integer[arr_len];
            for (int j=0; j<arr_len; j++) arr[j] = random.nextInt(1, 100);

            arrs.add(arr);
        }

        // make them all into trees
        ArrayList<AVLTree<Integer>> trees = new ArrayList<>();
        trees.add(new AVLTree<>(arrs.get(0)));
        trees.add(new AVLTree<>(arrs.get(1)));
        trees.add(new AVLTree<>(arrs.get(2)));
        trees.add(new AVLTree<>(arrs.get(3)));
        trees.add(new AVLTree<>(arrs.get(4)));

        // test each tree to make sure that it is an AVL tree
        trees.forEach(tree -> TestAVLTree(tree));
    }
}
