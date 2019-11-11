package com.xiaozhou.dp;
/*
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
    思路： 对于rabbbit 和 rabbit  两个b的排列组合有三种。
 */
class Solution115 {
    public int numDistinct(String S, String T) {
        int[][] mem = new int[T.length() + 1][S.length() + 1];
        for (int j = 0; j <= S.length(); j++) {
            mem[0][j] = 1;
        }
        // i 是短的 j 是长的 。
        for (int i = 0; i < T.length(); i++) {
            for (int j = 0; j < S.length(); j++) {

                if (T.charAt(i) == S.charAt(j)) {

                    mem[i + 1][j + 1] = mem[i][j] + mem[i + 1][j];
                } else {
                    // 不匹配的话就说明是多余的。 rab -> ra 与ra -> ra 多了个b，但是b不匹配T里的字符， 所以有57行代码。
                    mem[i + 1][j + 1] = mem[i + 1][j];
                }
            }
        }

        return mem[T.length()][S.length()];
    }
}
