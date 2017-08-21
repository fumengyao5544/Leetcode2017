// 1. Inorder
public class Solution {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode min = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void inorder(TreeNode root){
        if (root == null)return;
        inorder(root.left);
        if (root.val < min.val) {
            if (first == null){
                first = min;
            }
            second = root;
        }
        min = root;
        inorder(root.right);
    }
}





// 2. Morris Traversal
public class Solution {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode min = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null){
            // Case 1: no left child --> print current
            if (curr.left == null){
                check(curr);
                curr = curr.right;
            }else {
                // Case 2: has left child
                // Step 1: find predecessor in left subtree
                prev = curr.left;
                while (prev.right != null && prev.right != curr){
                    prev = prev.right;
                }
                // Step 2 : connect predecessor and current
                // Case 2.1: not connected
                if (prev.right == null){
                    prev.right = curr;
                    curr = curr.left;
                }else {
                    // Case 2.2: connected --> reset prev.right, print current
                    prev.right = null;
                    check(curr);
                    curr = curr.right;
                }
            }
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void check(TreeNode root){
        if (root.val < min.val) {
            if (first == null){
                first = min;
            }
            second = root;
        }
        min = root;
    }
}
