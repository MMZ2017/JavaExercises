
public class MinDepthOfBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    } 
    if (root.left == null) {
      return minDepth(root.right) + 1;
    }
    if (root.right == null) {
      return minDepth(root.left) + 1;
    } 
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;   
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;  
    node2.right = node5;
    node3.left = node6;
    
    System.out.println(minDepth(node1));
  }
}
