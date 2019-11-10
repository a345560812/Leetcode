package com.xiaozhou.dp;

/**
 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 Example 1:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true
 Example 2:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false
 思路:这道题拿到手首先想的是设置3个索引，用递归的方式对s3的每个字符逐一去比对s1和s2.然后对于s3Index的字符既等于s1Index的字符又等于
 s2Index的字符时就做一个||操作。思路是对的，但是时间复杂度比DP要差的很远(???我以为会比DP的快)
 */
class Solution97 {
    // DP解法
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        for(int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);

        }
        for(int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);

        }
        for(int i = 1; i <= s1.length();i++) {
            for(int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) ||
                        (dp[i][j - 1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
    // 递归解法
    public boolean isInterleave2(String s1, String s2, String s3) {
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return helper(index1, index2, index3,s1, s2, s3);
    }

    public boolean helper(int i1, int i2, int i3, String s1, String s2, String s3) {
        if(i3 == s3.length()) {
            return true;
        }

        if(i1 >= s1.length()) {
            if(s2.charAt(i2) != s3.charAt(i3)) {
                return false;
            } else {
                return helper(i1,i2+1,i3+1, s1, s2, s3);
            }
        }

        if(i2 >= s2.length()) {
            if(s1.charAt(i1) != s3.charAt(i3)) {
                return false;
            } else {
                return helper(i1+1, i2, i3+1, s1, s2, s3);
            }
        }
        if(s1.charAt(i1) == s2.charAt(i2) && s2.charAt(i2) == s3.charAt(i3)) {
            return helper(i1+1, i2,i3+1,s1, s2, s3) ||
                    helper(i1,i2+1,i3+1,s1, s2, s3);
        }
        if(s1.charAt(i1) == s3.charAt(i3)) {
            return helper(i1+1, i2, i3+1, s1, s2, s3);
        }
        if(s2.charAt(i2) == s3.charAt(i3)) {
            return helper(i1, i2+1, i3+1,s1, s2, s3);
        }
        return false;
    }
}
