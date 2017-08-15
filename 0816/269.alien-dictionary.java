public class Solution {
	//General idea: Topological sort
    private HashMap<Character, Set> graph; //graph of all characters
    private HashMap<Character,Integer> inDegree; //indegree for each character, used for topological sorting

    public String alienOrder(String[] words) {
        initialize(words);
        buildGraph(words);
        return topologicalSort();
    }
//========================= PRIVACY WARNING ++++++++++++++++++++++++++//
    private void initialize(String[] words) {
    	graph = new HashMap<>();
    	inDegree =new HashMap<>();
    	for(String word : words) {
    		for (int i = 0; i < word.length(); i++) {
    			if(!graph.containsKey(word.charAt(i))) {
    				graph.put(word.charAt(i), new HashSet<>());
    			}
    			if(!inDegree.containsKey(word.charAt(i))) {
    				inDegree.put(word.charAt(i), 0);
    			}
    		}
    	}
    }

    private void buildGraph(String[] words) {
    	for(int i = 0; i < words.length-1; i++) {
    		String firstWord = words[i];
    		String secondWord = words[i+1];
    		for(int j = 0; j < Math.min(firstWord.length(), secondWord.length()); j++) {
    			if (firstWord.charAt(j) == secondWord.charAt(j)) {
    				continue;
    			}
    			//reaching here means there is a discrepency
    			// System.out.println(firstWord.charAt(j) + " is greater than "+ secondWord.charAt(j));
    			Set<Character> set = graph.get(firstWord.charAt(j));
    			if (!set.contains(secondWord.charAt(j))) {
    				//BUGWARNING: de-dup!
    				set.add(secondWord.charAt(j));
    				inDegree.put (secondWord.charAt(j), inDegree.get(secondWord.charAt(j))+1);
    			}
    			//BUGWARNING: this is easy to forget but is very very very important
    			break;
    		}

    	}
    }

    private String topologicalSort() {
    	StringBuilder sb =  new StringBuilder();
    	Deque<Character> queue =  new ArrayDeque<>();
    	for (Character c : graph.keySet()) {
    		if (inDegree.get(c) == 0) {
    			queue.add(c);
    		}
    	}
    	while(!queue.isEmpty()) {
    		char front = queue.remove();
    		sb.append(front);
    		Set<Character> children = graph.get(front);
    		for(char c : children) {
    			int in = inDegree.get(c);
    			in --;
    			if (in  == 0) {
    				queue.add(c);
    			}
    			inDegree.put(c, in);
    		}
    	}
    	// System.out.println(sb.toString());
    	//BUGWARNING: length()? size()?
    	if (sb.length() == inDegree.size()) {
    		return sb.toString();
    	} else {
    		return "";
    	}

    }
}