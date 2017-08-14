
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int j = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (i = 0; i < nums.length; i++) {
            while ( j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) {
                result = Math.min(result, j - i);
            }
            sum -= nums[i];
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
