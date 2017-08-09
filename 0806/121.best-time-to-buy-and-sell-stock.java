public class Solution {
    public int maxProfit(int[] prices) {
        int minIndex = 0;
        int maxProfit = 0;
        if (prices.length < 2) {
        	return 0 ;
        }
        for(int i= 1; i < prices.length; i++) {
        	if(prices[i] >= prices[minIndex]) {
        		maxProfit = Math.max(maxProfit,prices[i] - prices[minIndex]);
        	} else {
        		minIndex = i;
        	}
        }
        return maxProfit;
    }
}