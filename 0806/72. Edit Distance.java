/*
72. Edit Distance

Given two words word1 and word2, find the minimum number of steps required
to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/
public class Solution {
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		// 初始化空字符串的情况
		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= n; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 增加操作：str1a变成str2后再加上b，得到str2b
				int insertion = dp[i][j - 1] + 1;
				// 删除操作：str1a删除a后，再由str1变为str2b
				int deletion = dp[i - 1][j] + 1;
				// 替换操作：先由str1变为str2，然后str1a的a替换为b，得到str2b
				int replace = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
				// 三者取最小
				dp[i][j] = Math.min(replace, Math.min(insertion, deletion));
			}
		}
		return dp[m][n];
	}
}
