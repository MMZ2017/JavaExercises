
public class SameTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static boolean isSameTree(TreeNode p, TreeNode q) {
    TreeNode currentP = p;
    TreeNode currentQ = q;
    if (currentP == null && currentQ == null) {
      return true;
    } else if ((currentP == null && currentQ != null) || (currentP != null && currentQ == null)) {
      return false;
    } else {
      return isSameTree(currentP.left, currentQ.left) && isSameTree(currentP.right, currentQ.right) 
          && currentP.val == currentQ.val;
    }
    
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;

    TreeNode nodeA = new TreeNode(1);
    TreeNode nodeB = new TreeNode(2);
    TreeNode nodeC = new TreeNode(3);
    TreeNode nodeD = new TreeNode(5);
    nodeA.left = nodeB;
    nodeA.right = nodeC;
    nodeB.left = nodeD;
    
    System.out.println(isSameTree(node1, nodeA));
  }
}
