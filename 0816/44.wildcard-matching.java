public class Solution {
	public boolean isMatch(String s, String p) {
		return isMatchTwoPointers(s,p);
	}
    public boolean isMatchDP(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j <= n; j++) {
        	if (p.charAt(j-1) == '*'){
        		dp[0][j] = dp[0][j-1];
        	}
        }
        for(int i = 1 ; i <= m; i++) {
        	for (int j = 1; j <=n; j++) {
        		if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) =='?') {
        			dp[i][j] |= dp[i-1][j-1];
        		} else if (p.charAt(j-1) =='*') {
        			dp[i][j] |= dp[i][j-1];
        			dp[i][j] |= dp[i-1][j];
        		}
        	}
        }
        return dp[m][n];
    }
    public boolean isMatchTwoPointers(String s, String p) {
    	int indexS = 0;
    	int indexP = 0;

    	int m = s.length();
    	int n = p.length();

    	int preS = 0;
    	int preP = 0;

    	boolean back = false;

    	while ( indexS < m) {
    		if(indexP < n && matchChar(s.charAt(indexS), p.charAt(indexP))) {
    			indexP++;
    			indexS++;
    		} else if (indexP < n && p.charAt(indexP) == '*') {
    			while(indexP < n && p.charAt(indexP) == '*') {
    				indexP++;
    			}

    			if (indexP == n) {
    				return true;
    			}

    			back = true;
    			preS = indexS;
    			preP = indexP;
    		} else {
    			if (back) {
    				indexS = ++preS;
    				indexP = preP;
    			} else {
    				return false;
    			}
    		}

    	}
    	while (indexP < n && p.charAt(indexP) == '*') {
    		indexP++;
    	}

    	if (indexS == m && indexP ==n) {
    		return true;
    	} else {
    		return false;
    	}
    }
    private boolean matchChar(char s, char p) {
    	return (s == p || p == '?');
    }
}