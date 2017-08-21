Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ? j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.



public class Solution {
    long[] counts;
    int lowerBound;
    int upperBound;
    public int countRangeSum(int[] nums, int lower, int upper) {
        lowerBound = lower;
        upperBound = upper;
        if (nums.length <= 0) return 0;
        counts = new long[nums.length];
        counts[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            counts[i] = counts[i - 1] + nums[i];
        }
        return helper (0, nums.length - 1,nums);
    }
    private int helper(int left, int right, int[] nums){
        if (left == right){
            if (nums[left] >= lowerBound && nums[right] <= upperBound){
                return 1;
            }
            return 0;
        }
        int mid = (left + right) / 2;
        int total = 0;
        for (int i = left; i <= mid; i++) {
            for (int j = mid + 1; j <= right; j++){
                long temp = counts[j] - counts[i] + nums[i];
                if (temp >= lowerBound && temp <= upperBound){
                    total++;
                }
            }
        }
        return total + helper(left, mid, nums) + helper(mid + 1, right, nums);
    }
}
