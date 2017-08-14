
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean helper (TreeNode node, Long min, Long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return helper(node.left, min, (long)node.val) && helper(node.right, (long)node.val, max);
    }
}
