public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return new String();
        Stack<String> stack = new Stack<>();
        String[] s = path.split("/");
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(".") || s[i].equals("")) {
                continue;
            }else if (!s[i].equals("..")) {
                stack.push(s[i]);
            }else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        Stack<String> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        if (temp.isEmpty()) {
            temp.push("");
        }
        StringBuilder sb = new StringBuilder();
        while (!temp.isEmpty()) {
            sb.append("/");
            sb.append(temp.pop());
        }
        return sb.toString();
    }
}
