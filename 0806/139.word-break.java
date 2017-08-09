public class Solution {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<String>();
		for(String word : wordDict) {
			dict.add(word);
		}
		 //Have to use DP
		boolean[] dp = new boolean[s.length() +1];
		Arrays.fill(dp,false);
		//set seed
		dp[0] = true;
		for(int i = 0; i < s.length() + 1; i++) {
			for(int j = 0; j < i; j++) {
				if (dp[j] && dict.contains(s.substring(j,i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}
  //   public boolean wordBreak(String s, List<String> wordDict) {

		// }
		// return helper(s, dict);        
  //   }
  //   private boolean helper(String s, Set<String> dict) {
  //   	if (s.length() == 0) {
  //   		return true;
  //   	}
  //   	boolean ret = false;
  //   	for(int i = 1; i <= s.length(); i++) {
  //   		String cand = s.substring(0,i);
  //   		if (dict.contains(cand)) {
  //   			ret |= helper(s.substring(i),dict);
  //   		}
  //   	}
  //   	return ret;
  //   }
}