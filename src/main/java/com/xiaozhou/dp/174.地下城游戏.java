package com.xiaozhou.dp;
/*
Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
Accepted
思路：这道题很有意思， 有点魔塔的那个小游戏的感觉。这道题开始的思路是设了两个二维数组，然后从左上往右下推导。但是这样存在
一个问题，就是无法归纳所有情况进来， 代码只能满足题目给定的CASE, 如果要满足所有CASE会增加很多冗余代码。
所以考虑从后往前推导。 可以理解成让公主去救王子。以公主救王子的思路来做这道题。
 */
class Solution174 {
    public static void main(String[] args) {
        int[][] num = new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}};
        calculateMinimumHP(num);
    }
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] M = new int[m][n];
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                //初始条件
                if(i == m - 1 && j == n - 1){
                    M[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
                }else if(i == m - 1){
                    // 初始条件
                    M[i][j] = Math.max(1, M[i][j+1] - dungeon[i][j]);
                }else if(j == n - 1){
                    // 初始条件
                    M[i][j] = Math.max(1,M[i+1][j] - dungeon[i][j]);
                }else{
                    // DP状态方程式。 这个式子有3个Math函数， 一个Math内嵌了两个Math
                    // 内嵌的两个Math是指公主走完当前这个门所需的最少的血量（公主的血量始终要大于1）
                    // 外面的Math.min就是说公主走到当前的门花费最少血量的最佳路径。
                    M[i][j] = Math.min(Math.max(1, M[i+1][j] - dungeon[i][j]), Math.max(1, M[i][j+1] - dungeon[i][j]));
                }
            }
        }
        return M[0][0];
    }
}


