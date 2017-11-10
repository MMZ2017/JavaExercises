import java.util.ArrayList;
import java.util.List;

public class AvgOfLevels {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
      val = x;
    }
  }
  
  public static List<Double> averageOfLevels(TreeNode root) {
    ArrayList<Double> avgArr = new ArrayList<Double>();
    avgHelper(root, avgArr);
    return avgArr;
  }
  
  private static void avgHelper(TreeNode current, ArrayList<Double> avgArr) {
    ArrayList<TreeNode> prevArr = new ArrayList<TreeNode>();
    ArrayList<TreeNode> nextArr = new ArrayList<TreeNode>();   
    nextArr.add(current);
    while (!nextArr.isEmpty()) {     
      double sum = 0;
      int count = 0;
      prevArr = nextArr;
      nextArr = new ArrayList<TreeNode>();
      for (TreeNode tn : prevArr) {
        if (tn.left != null) {
          nextArr.add(tn.left);
        }
        if (tn.right != null) {
          nextArr.add(tn.right);
        }        
        sum = sum + tn.val;
        count++;
      }
      avgArr.add(sum/count);
    }  
  }
  
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(9);
    TreeNode node3 = new TreeNode(20);
    TreeNode node4 = new TreeNode(15);
    TreeNode node5 = new TreeNode(7);
    
    node1.left = node2;
    node1.right = node3; 
    node3.right = node5;
    node3.left = node4;

    for (double avg : averageOfLevels(node1)) {
      System.out.println(avg);
    }
  }
}
