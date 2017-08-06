// A message containing letters from A-Z is being encoded to numbers using the following mapping way:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

// Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

// Also, since the answer may be very large, you should return the output mod 109 + 7.

// Example 1:
// Input: "*"
// Output: 9
// Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
// Example 2:
// Input: "1*"
// Output: 9 + 9 = 18



// Method: DP
// 1. i-1  == '*'
//    dp[i] += dp[i - 1] * 9
//   (1) i-2 == '*':   dp[i] += dp[i - 2] * 15
//   (2) i-2 == '1':   dp[i] += dp[i - 2] * 9
//   (3) i-2 == '2':   dp[i] += dp[i - 2] * 6
// 2. i-1 != '*'
//    i-1 != '0':   dp[i] += dp[i - 1]
//    (1) i-2 == '*':
//          (1) i-1 <= 6: dp[i] += dp[i - 2] * 2
//          (2) i-1 > 6:  dp[i] += dp[i - 2] 
//    (2) i-2 != '*'
//           10<= number <=26: dp[i] += dp[i - 2] 
  

// Attention: 
// 1.要mod一个数
// 2.乘的倍数要变成long


public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0'){
            dp[1] = 0;
        }else if (s.charAt(0) == '*'){
            dp[1] = 9;
        }else {
            dp[1] = 1;
        }
        final int mod = 1000000007;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '*'){
                dp[i] = (int)((dp[i] + 9L * dp[i - 1])% mod);
                if (s.charAt(i - 2) == '*') {
                    dp[i] = (int)((dp[i] + 15L * dp[i - 2]) % mod);    
                }else if (s.charAt(i - 2) == '1'){
                    dp[i] = (int)((dp[i] + 9L * dp[i - 2]) % mod);   
                }else if (s.charAt(i - 2) == '2'){
                    dp[i] = (int)((dp[i] + 6L * dp[i - 2]) % mod);
                }
            }
            else {
                if (s.charAt(i - 1) != '0') {
                    dp[i] = (dp[i] + dp[i - 1]) % mod;
                }
                
                if (s.charAt(i - 2) == '*') {
                    if (s.charAt(i - 1)  <= '6') {
                        dp[i] = (int)((dp[i] + 2L * dp[i - 2]) % mod);
                    }else {
                        dp[i] = (dp[i] + dp[i - 2]) % mod;
                    }
                }else {
                    int number = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                    if (number >= 10 && number <= 26){
                        dp[i] = (dp[i] + dp[i - 2]) % mod;
                    }
                }
            }
        }
        return dp[s.length()];
    }
}
        
