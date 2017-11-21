

public class SymmetricTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static boolean isSymmetric(TreeNode root) {
    return root == null ? true : isMirror(root.left, root.right);
  }
  
  private static boolean isMirror(TreeNode left, TreeNode right) {
    return left == null || right == null ? left == null && right == null
        : left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(2);
    TreeNode node4 = new TreeNode(3);
    TreeNode node5 = new TreeNode(4);
    TreeNode node6 = new TreeNode(4);
    TreeNode node7 = new TreeNode(3);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    
    System.out.println(isSymmetric(node1));
    System.out.println(node1.left.val);
    System.out.println(node2.right.val);
  }
}
