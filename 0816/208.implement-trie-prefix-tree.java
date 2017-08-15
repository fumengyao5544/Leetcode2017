public class Trie {
    class TrieNode {
        private char val;
        private Map<Character, TrieNode> children;
        private boolean isEnd;
        public TrieNode() {
            children = new HashMap<>();
            val = '\0';
            isEnd = false;
        }
        public TrieNode(char val) {
            this.val = val;
            children = new HashMap<>();
            isEnd = false;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
       root = new TrieNode(); 
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode iter = root;
        for(char c : word.toCharArray()) {
            if (! iter.children.containsKey(c)) {
                iter.children.put(c, new TrieNode(c));
            } 
            iter = iter.children.get(c);
        }
        iter.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
       if (word.length() == 0) {
            return true;
       } 
       TrieNode iter = root;
       for (char c : word.toCharArray()) {
        if (!iter.children.containsKey(c)) {
            return false;
        }
        iter = iter.children.get(c);
       }
       return iter.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
         if (prefix.length() == 0) {
            return true;
       } 
       TrieNode iter = root;
       for (char c : prefix.toCharArray()) {
        if (!iter.children.containsKey(c)) {
            return false;
        }
        iter = iter.children.get(c);
       }
       return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */