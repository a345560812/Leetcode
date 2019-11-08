package com.xiaozhou.dp;
/*
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
思路：和62题类似 基本没差。
 */
class Solution63 {
    public int uniquePathsWithObstacles(int[][] nums)
    {
        int[][] dp = new int[nums.length][nums[0].length];
        for(int i = 0; i < dp.length; i++) {
            if(nums[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }

        for(int i = 0; i < dp[0].length; i++) {
            if(nums[0][i] == 1) {
                break;
            } else {
                dp[0][i] = 1;
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length;j++) {
                if(nums[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }
}
