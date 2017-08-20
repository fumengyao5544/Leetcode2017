/*
20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid
but "(]" and "([)]" are not.
*/
public class Solution {
	public boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case '(':
					stack.push('(');
					break;
				case '{':
					stack.push('{');
					break;
				case '[':
					stack.push('[');
					break;
				case ')':
					if (stack.size() == 0 || stack.pop() != '(') {
						return false;
					}
					break;
				case '}':
					if (stack.size() == 0 || stack.pop() != '{') {
						return false;
					}
					break;
				case ']':
					if (stack.size() == 0 || stack.pop() != '[') {
						return false;
					}
					break;
			}
		}
		return stack.isEmpty();
	}
}