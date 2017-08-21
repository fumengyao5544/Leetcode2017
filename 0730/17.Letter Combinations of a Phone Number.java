// Given a digit string, return all possible letter combinations that the number could represent.

// A mapping of digit to letters (just like on the telephone buttons) is given below.

  
// Input:Digit string "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// Note:
// Although the above answer is in lexicographical order, your answer could be in any order you want.

  
  public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0 ) {
            return result;
        }
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('0', new char[]{});
        map.put('1', new char[]{});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        StringBuilder sb = new StringBuilder();
        helper (digits, map, result, sb);
        return result;
    }
    
    public void helper (String digits, HashMap<Character, char[]> map, List<String> result, StringBuilder sb){
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        for (char ch : map.get(digits.charAt(sb.length()))) {
            sb.append(ch);
            helper(digits, map, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
