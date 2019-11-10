package com.xiaozhou.dp;
/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   思路：以任意一个值做根节点，满足左小右大。dp[i] += dp[j]*dp[i-j-1] 就是表示根节点为i时平衡二叉树的个数。
   dp[i]表示二叉树节点个数为i时它所包含不同平衡二叉树的个数， dp[j]等于左子树的个数，dp[i-j-1]等于右子树个数。
 */
public class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j - 1];
            }
        }
        return dp[n];
    }
}
