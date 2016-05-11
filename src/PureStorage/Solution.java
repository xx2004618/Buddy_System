package PureStorage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    private TreeNode root;
    public Solution() {
        root = new TreeNode(0);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(0);
        TreeNode node8 = new TreeNode(0);
        TreeNode node9 = new TreeNode(0);
        TreeNode node10 = new TreeNode(0);
        TreeNode node11 = new TreeNode(0);
        TreeNode node12 = new TreeNode(0);
        TreeNode node13 = new TreeNode(0);
        TreeNode node14 = new TreeNode(0);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
        node4.left = node9;
        node4.right = node10;
        node5.left = node11;
        node5.right = node12;
        node6.left = node13;
        node6.right = node14;
    }
    
    public void clearBit(int offset, int len) {
        if (offset <= 0 || len <= 0 || len > offset * offset) {
            return;
        }
        clearHelper(root, offset, len);
    }
    
    private void clearHelper(TreeNode root, int offset, int len) {
        if (root == null) {
            return;
        }
        root.key = 0;
        if (offset == 1) {
            return;
        }
        int temp = 1;
        int n = offset - 2;
        while (n > 0) {
            temp = temp * 2;
            n--;
        }
        if (len > temp) {
            clearHelper(root.right, offset - 1, len - temp);
        } else {
            clearHelper(root.left, offset - 1, len);
        }
    }
    
    public void setBit(int offset, int len) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (offset <= 0 || len <= 0 || len > offset * offset) {
            return;
        }
        setHelper(root, offset, len, stack);
    }
    
    private void setHelper(TreeNode root, int offset, int len, Stack<TreeNode> stack) {
        if (root == null) {
            setStack(stack);
            return;
        }
        if (offset == 1) {
            setRest(root);
            setStack(stack);
        } else {
            stack.push(root);
            int temp = 1;
            int n = offset - 2;
            while (n > 0) {
                temp = temp * 2;
                n--;
            }
            if (len > temp) {
                setHelper(root.right, offset - 1, len - temp, stack);
            } else {
                setHelper(root.left, offset - 1, len, stack);
            }
        }
    }
    
    private void setStack(Stack<TreeNode> stack) {
        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.left != null && temp.left.key == 1) {
                if (temp.right == null || temp.right.key == 1) {
                    temp.key = 1;
                } else {
                    temp.key = 0;
                }
            } else {
                temp.key = 0;
            }
        }
    }
    
    private void setRest(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            temp.key = 1;
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
    }
    
    public void display() {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int number = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
                number++;
                System.out.println(number + ": " + temp.key + " ");
            }
            System.out.println("-----------");
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Solution solution = new Solution();
        solution.setBit(3, 3);
        solution.setBit(3, 4);
        solution.clearBit(4, 7);
        solution.display();
    }

}
