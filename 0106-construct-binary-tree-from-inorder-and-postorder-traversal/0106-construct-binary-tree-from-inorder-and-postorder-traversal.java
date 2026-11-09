/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int postIndex;
    private Map<Integer, Integer> inorderIndex;
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.postIndex = postorder.length - 1;
        this.inorderIndex = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        return build(0, inorder.length - 1);
    }

    private TreeNode build(int left, int right) {
        if (left > right) {
            return null;
        }

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        int mid = inorderIndex.get(rootVal);

        root.right = build(mid + 1, right);
        root.left = build(left, mid - 1);

        return root;
    }
}