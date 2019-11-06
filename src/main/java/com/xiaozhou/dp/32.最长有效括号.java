package com.xiaozhou.dp;

import java.util.Stack;

/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
思路:求最长有效的括号匹配数，对于这种括号匹配的题，大部分思路都是碰到左括号进栈，碰到右括号然后进行一堆乱七八糟的操作。具体如下
 */
class Solution32 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int l = 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
            if(c[i] == '(') {
                stack.push(i);
            } else {
                // 碰到右括号时栈为空，说明出现该右括号无效，l代表有效匹配括号字符串的起始索引。
                if(stack.isEmpty()) {
                    l = i + 1;
                } else {
                    // 栈不为空分两种情况，刚好匹配完和还有多的左括号。
                    stack.pop();
                    if(stack.isEmpty()) {
                        count = Math.max(count, i - l + 1);
                    } else {
                        // 这种情况一定说明是左括号比右括号数量多的情况, 由于每次栈不为空并且碰到右括号时，栈会出栈，
                        // 所以stack.peek()代表的是多余的左括号中索引最大的那个。 举例可以考虑((()()这个情况进行验证。
                        count = Math.max(count, i - stack.peek());
                    }
                }
            }
        }
        return count;
    }
}
