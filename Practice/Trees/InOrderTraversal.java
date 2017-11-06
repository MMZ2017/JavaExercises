public class InOrderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static void inOrderTraversal(TreeNode current) {
    if (current != null) {
      inOrderTraversal(current.left);
      doSomething(current);
      inOrderTraversal(current.right);
    } 
  } 
  
  private static void doSomething(TreeNode tn) {
    System.out.println(tn.val);
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

    inOrderTraversal(node1);
  }
}
