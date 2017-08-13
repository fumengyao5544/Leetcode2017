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