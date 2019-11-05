package dynamicprogramming;
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"
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

    }
}
