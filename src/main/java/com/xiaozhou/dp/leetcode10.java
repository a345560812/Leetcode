package com.xiaozhou.dp;
/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false

思路 这道题我学了递归和DP两种方案解决，DP的时间复杂度是O(s*p),递归的话我个人认为是O(Math.math(s,p)^3)
 */
public class leetcode10 {
    public boolean isMatch(String s, String p) {
        // return recurSionHelper(s.toCharArray(), 0, p.toCharArray(), 0);
        return dpHelper(s.toCharArray(),p.toCharArray());
    }
    /*
    递归的思路是这样：对正则表达式和字符串分别设置一个索引，首先考虑递归终止条件(正则表达式和字符串的索引都成功遍历完尾部)
    ，考虑递归的推进式，只有根据索引判断当目前正则表达式能和字符串匹配上的时候，索引往后推进。在这里推进有两种情况
    ①正则表达式的索引后一位所在的字符 == '*'
    这种情况又分成了3种子情况考虑
    (1)*号匹配1个字符，正则表达式的索引所在的字符等于'.'或者和字符串索引所在字符相等，说明能匹配上往后推进，各自索引+1.
    (2)*号匹配了0个字符. 比如s = "ss"和p = "sp*s", 这种情况p*可以直接跳过去，所以正则表达式的索引+2，字符串索引不动。
    (3)*号匹配了多个字符，这种情况条件和(1)应该要求一致，此时正则表达式索引不动，字符串索引+1.
    ②正则表达式的索引往后一位所在的字符 != '*'
    这种情况下只有正则表达式索引所在字符 == '.'或者正则表达式索引所在的字符和字符串索引所在的字符相等才能往后推进。
    通过上述推进思路抵达终点则说明可匹配。
     */
    public boolean recurSionHelper(char[] str, int strIndex, char[] pattern, int patternIndex) {
        if(strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }

        if(strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }

        if(patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (strIndex != str.length
                    && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')
            ) {
                return recurSionHelper(str, strIndex + 1, pattern, patternIndex + 2)
                        || recurSionHelper(str, strIndex, pattern, patternIndex + 2)
                        || recurSionHelper(str, strIndex + 1, pattern, patternIndex);
            } else {
                return recurSionHelper(str, strIndex, pattern, patternIndex + 2);
            }
        }
        if(strIndex != str.length
                && (str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')
        ) {
            return recurSionHelper(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
    /*
    DP的思路实质上和递归是类似的，下面会在有区别的地方做下注释。
     */
    public boolean dpHelper(char[] str, char[] pattern) {
        //到105行为止都是初始赋值过程。dp[i+1][j+1]表示字符串的索引推进到i,正则表达式的索引推进到j时 它们目前为止还是互相匹配的
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for(int i = 0; i < pattern.length; i++) {
            if(pattern[i] == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for(int i = 0; i <str.length; i++) {
            for(int j = 0; j < pattern.length; j++) {
                if(str[i] == pattern[j] || pattern[j] == '.') {
                    // 110行代码赋值的含义可以理解成这样: 正则表达式和
                    dp[i+1][j+1] = dp[i][j];
                } else if(pattern[j] == '*') {
                    if(str[i] != pattern[j-1] && pattern[j-1] != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = dp[i+1][j-1] || dp[i+1][j]||dp[i][j+1];
                    }
                }
            }
        }

        return dp[str.length][pattern.length];
    }
}
