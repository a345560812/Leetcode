package com.xiaozhou.dp;

import java.util.ArrayList;
import java.util.List;
/*
Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 思路:这道题我学的是一种递归的解法。理解起来比较清楚（其实也并不清楚- -，智商有限)，总体来说
 就是把二叉树的节点进行递归求解。
 */
class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();
        if(n < 1) {
            return list;
        }
        return helper(1, n);

    }

    public List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(left > right){
            res.add(null);
            return res;
        }
        for(int i = left; i <= right; i++) {
        /*
        这个递归理解起来很费劲，比如left = 1, right = 9, i=5时,leftRes实际上是1234这4个节点组成所有可能的左子树,
        怎么理解这个结论呢，想一下调用helper(1,4）然后又会有个for循环i的值从1到4，也就是说i=5时它的左子树的根节点可能是1到4
        就是说i= 5时它的左子树根节点可能是1234当中的一个，右子树的根节点是6789的一个，然后左子树假如为1，那么1就还有右子树234
        只要满足平衡条件即可， 平衡条件在40-43行进行限定。
        */
            List<TreeNode> leftTree = helper(left,i-1);
            List<TreeNode> rightTree = helper(i+1,right);
            for(int m = 0; m < leftTree.size();m++) {
                for(int n = 0; n < rightTree.size();n++) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftTree.get(m);
                    node.right = rightTree.get(n);
                    res.add(node);
                }
            }

        }
        return res;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    public TreeNode(int val) {
        this.val = val;
    }
}
