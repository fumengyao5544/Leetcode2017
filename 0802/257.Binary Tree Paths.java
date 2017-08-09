// Given a binary tree, return all root-to-leaf paths.
// For example, given the following binary tree:
//    1
//  /   \
// 2     3
//  \
//   5
// All root-to-leaf paths are:

// ["1->2->5", "1->3"]


public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        String s = "";
        if (root == null) return result;
        helper (root, result, s);
        return result;
    }
    public void helper (TreeNode node, List<String> result, String s) {
        if (node == null) {
            return;
        }
        s = s + String.valueOf(node.val) + "->";
        if (node.left == null && node.right == null) {
            result.add(new String(s.substring(0, s.length() - 2)));
        }
        helper(node.left, result, s);
        helper(node.right, result, s);
    }
}
