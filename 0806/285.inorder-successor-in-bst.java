/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	//Key point:
    	//1. Remember the last parent where iter goes left.
    	//2. Take care if iter is not found
    	//3. If has right child, return right successor, else return the recorded parent
        TreeNode parent = null;
        TreeNode iter = root;
        while(iter != null && iter.val != p.val) {
        	if(p.val < iter.val) {
        		parent = iter;
        		iter = iter.left;
        	} else {
        		iter = iter.right;
        	}
        }
        if (iter == null) return null;
        if (iter.right == null) return parent;
        else {
        	iter = iter.right;
        	while(iter.left != null) {
        		iter = iter.left;
        	}
        }
        return iter;
    }
}