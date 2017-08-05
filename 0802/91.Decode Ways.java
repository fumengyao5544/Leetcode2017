
// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.

// For example,
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

// The number of ways decoding "12" is 2.

public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.equals("0")) return 0;
        if (s.length() == 1 ) return 1;
        int[] dp = new int[s.length() + 1];
       
         // dp[0] 不能设为0  ！！！
        dp[0] = 1;
        if (s.charAt(0) == '0'){
            dp[1] = 0;
        }else {
            dp[1] = dp[0];
        }
      
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
