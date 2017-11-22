public class TrimBst {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static TreeNode trimBST(TreeNode root, int L, int R) {
    return trimHelper(root, L, R);
  }
  
  private static TreeNode trimHelper(TreeNode current, int L, int R) {
    if (current == null) {
      return current;
    }
    if (current.val < L) {
      current = trimHelper(current.right, L, R);
    } else if (current.val > R) {
      current = trimHelper(current.left, L, R);
    } else {
      current.left = trimHelper(current.left, L, R);
      current.right = trimHelper(current.right, L, R);
    }
    return current;   
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(4);
    TreeNode node4 = new TreeNode(2);
    TreeNode node5 = new TreeNode(1);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(8);
    node1.left = node2;
    node1.right = node3; 
    node2.right = node4;
    node4.left = node5;

    TreeNode nodeTest = trimBST(node1, 1, 3);
    System.out.println(nodeTest.val);
    System.out.println(nodeTest.left.val);
  }
}
