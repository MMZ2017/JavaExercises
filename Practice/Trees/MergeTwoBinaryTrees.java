public class MergeTwoBinaryTrees {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    TreeNode current1 = t1;
    TreeNode current2 = t2;
    if (current1 == null & current2 == null) {
      return null;
    } else if (current1 != null && current2 == null) {
      return current1;
    } else if (current1 == null && current2 != null) {
      return current2;
    } else {
      TreeNode current = new TreeNode(current1.val + current2.val);
      current.left = mergeTrees(current1.left, current2.left);
      current.right = mergeTrees(current1.right, current2.right);
      return current;
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
    
    System.out.println(mergeTrees(node1, nodeA).val);
  }
}
