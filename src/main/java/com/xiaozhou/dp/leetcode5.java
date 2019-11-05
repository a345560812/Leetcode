package com.xiaozhou.dp;
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"

思路：这道题要求对任意字符串找到他的最长回文子串， 思路就是分成两部分，
①找到所有的回文子串
②在所有回文子串中找到最长的那一个子串即可。
 */
public class leetcode5 {
    String ss = "";
    public String longestPalindrome(String s) {

        for(int i = 0; i < s.length(); i++) {
            findParalind(s, i, i);
            findParalind(s, i, i + 1);
        }
        return ss;
    }
    public void findParalind(String str, int l, int r) {

        while(l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
            l--;
            r++;
        }
        String sub = str.substring(l+1, r);
        ss = ss.length() > sub.length()?ss:sub;
    }
}

