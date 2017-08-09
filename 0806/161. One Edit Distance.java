/*
161. One Edit Distance

Given two strings S and T, determine if they are both one
edit distance apart.
*/
public class Solution {
	public boolean isOneEditDistance(String s, String t) {
		int m = s.length();
		int n = t.length();
		if (m == n) {
			return isOneModified(s, t);
		}
		if (m - n == 1) {
			return isOneDeleted(s, t);
		}
		if (n - m == 1) {
			return isOneDeleted(t, s);
		}
		// 长度差距大于2直接返回false
		return false;
	}
	
	// 看是否只修改了一个字符
	private boolean isOneModified(String s, String t){
		boolean modified = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (modified) {
					return false;
				}
				modified = true;
			}
		}
		return modified;
	}
	
	// 找到第一组不一样的字符，看后面是否一样
	private boolean isOneDeleted(String longer, String shorter){
		for (int i = 0; i < shorter.length(); i++) {
			if (longer.charAt(i) != shorter.charAt(i)) {
				return longer.substring(i + 1).equals(shorter.substring(i));
			}
		}
		return true;
	}
}
