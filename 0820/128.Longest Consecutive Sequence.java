/*
128. Longest Consecutive Sequence

Given an unsorted array of integers, 
find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity. 
*/
class Solution {
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		Set<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}
		int max = 1;
		for (int num : nums) {
			// num hasn't been visited
			if (set.remove(num)) {
				int val = num;
				int sum = 1;
				while (set.remove(val - 1)) {
					val--;
				}

				sum += num - val;
				val = num;
				while (set.remove(val + 1)) {
					val++;
				}
				sum += val - num;
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}