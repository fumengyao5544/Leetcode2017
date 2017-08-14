public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
        	return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : nums) {
        	heap.offer(i);
        	if (heap.size() > k) {
        		heap.poll();
        	}
        }
        return heap.peek();
    }
}




public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return Integer.MIN_VALUE;
        }
        return helper(nums, 0, nums.length - 1, nums.length - k);
    }
    
    public int helper (int[] nums, int start, int end, int k) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        int pivot = nums[end];
        int pos = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot){
                swap(nums, pos, i);
                pos++;
            }
        }
        swap(nums, pos, end);
        if (pos == k) {
            return nums[pos];
        }else {
            if (pos < k) {
                return helper(nums, pos + 1, end, k);
            }else {
                return helper(nums, start, pos - 1, k);
            }
        }
    }
    
    public void swap(int[] arr, int i, int j){
        if (arr[i] == arr[j]) {
            return;
        }else {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }
}
