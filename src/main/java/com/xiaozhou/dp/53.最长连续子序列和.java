package com.xiaozhou.dp;
/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
思路：这道题求最长连续子序列和。用一个变量来存最大值， 用dp数组求当前时刻的子序列最大值。
 */
class Solution53 {
    public int maxSubArray(int[] nums) {
        int max = 0;

        int[] dp = new int[nums.length];
        max = dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(0, dp[i-1]) + nums[i];
            max = Math.max(dp[i], max);
        }
        return max;

    }
}
