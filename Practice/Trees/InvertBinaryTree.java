public class InvertBinaryTree {
  
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static TreeNode invertTree(TreeNode root) {
    TreeNode current = root;
    if (current == null) {
      return null;
    } else {
      TreeNode temp = current.left;
      current.left = invertTree(current.right);
      current.right = invertTree(temp);
      return current;
    }
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node5.left = node7;
    
    invertTree(node1);
    System.out.println(node1.left.val);
    System.out.println(node2.right.val);
  } 
}
