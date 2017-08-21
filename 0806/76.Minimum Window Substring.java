// Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

// For example,
// S = "ADOBECODEBANC"
// T = "ABC"
// Minimum window is "BANC".

// Note:
// If there is no such window in S that covers all characters in T, return the empty string "".

// If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.


public class Solution {
    public String minWindow(String s, String t) {
        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] map = new int[128];
        int end = 0;
        int start = 0;
        int startIndex = 0;
        int minLen = Integer.MAX_VALUE;
        for(Character c : t_array){
            map[c]++;
        }
        int count = t_array.length;
        while (end < s_array.length){
            if (map[s_array[end]] > 0){
                count--;
            }
            map[s_array[end]]--;
            while (count == 0){
                if ((end - start + 1) < minLen){
                    minLen = end - start + 1;
                    startIndex = start;
                }
                map[s_array[start]]++;
                if (map[s_array[start]] > 0){
                    count++;
                }
                start++; // 一定要移动左指针
            }
            end++;// 一定要移动右指针
        }
        return minLen == Integer.MAX_VALUE ? new String() : s.substring(startIndex, startIndex + minLen);
    }
}
