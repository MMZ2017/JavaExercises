

public class SecondMinimumImproved {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static int findSecondMinimumValue(TreeNode root) {
    int secondMin = findSecondMinimumHelper(root);
    return secondMin == Integer.MAX_VALUE ? -1 : secondMin;  
  }
 
  private static int findSecondMinimumHelper(TreeNode current) {
    if (current == null) {
      return Integer.MAX_VALUE;
    }
    if (current.left == null && current.right == null) {
      return Integer.MAX_VALUE;
    }
    int left = current.left.val;
    int right = current.right.val;
    if (current.left.val == current.val) {
      left = findSecondMinimumHelper(current.left);
    }
    if (current.right.val == current.val) {
      right = findSecondMinimumHelper(current.right);
    } 
    return Math.min(left, right);     
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(6);
    TreeNode node4 = new TreeNode(2);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(8);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;  
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    
    System.out.println(findSecondMinimumValue(node1));
  }
}
