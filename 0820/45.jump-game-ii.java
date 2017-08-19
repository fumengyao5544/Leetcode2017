class Solution {
    public int jump(int[] nums) {
    	if (nums[0] == 0) {
    		return 0;
    	}
        int max = 1;
        int count = 0;
        int curMax = 0;
        int prevMax = 0;
        while(max < nums.length) {
        	//BUGWARNING: prevMax is here to save the time... why walk back all over again
        	prevMax = curMax;
        	curMax = max;
        	for(int i = prevMax; i < curMax; i++) {
        		max = Math.max(max, nums[i]+i+1);
        	}
        	count++;
        }
        return count;
    }
}