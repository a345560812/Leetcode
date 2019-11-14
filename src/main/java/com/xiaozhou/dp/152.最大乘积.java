package com.xiaozhou.dp;
/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
思路：要求最大乘积和，需要用两个变量记录当前最大值和最小值，因为当最小值为负数时乘以一个负数可能会变成最大。规避该类情况。
 */
class Solution152 {
    public int maxProduct(int[] nums) {
        int minNum=nums[0];
        int maxNum=nums[0];
        int res=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            int a = Math.min(minNum*nums[i], maxNum * nums[i]);
            int b = Math.max(minNum * nums[i], maxNum * nums[i]);
            minNum = Math.min(nums[i], a);
            maxNum = Math.max(nums[i], b);
            res = Math.max(res, maxNum);
        }
        return res;
    }
}
