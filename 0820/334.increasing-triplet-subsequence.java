class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
        	//we need to hava a triplet, to begin with
        	return false;
        }
        //THOUGHTS:
        //Stack solutions? One stack will not work here.
        //Have to store internal states, which of the smallest numbers are what we wanted.
       	int min = Integer.MAX_VALUE;
       	int second = Integer.MAX_VALUE;
       	for(int num : nums) {
       		if (num <= min) {
       			min = num;
       		} else if (num <= second) {
       			second = num;
       		} else return true;
       	}
       	return false;
    }
}