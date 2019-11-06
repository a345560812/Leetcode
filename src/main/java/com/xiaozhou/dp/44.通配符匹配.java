package com.xiaozhou.dp;
/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

思路:这道题和第10题基本差不多。写DP 第一个思路确定好初始值， 然后写出不动点方程。
 */
class Solution44 {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= p.length(); i++) {
            if(p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <p.length();j++) {
                if(p.charAt(j) != '*') {
                    if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                        dp[i+1][j+1] = dp[i][j];
                    } else {
                        dp[i+1][j+1] = false;
                    }
                } else {
                    // 按顺序看*号匹配了多个、1个、0个字符
                    dp[i+1][j+1] = dp[i+1][j] || dp[i][j] || dp[i][j+1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
