public class LFUCache {
	// Data structures
	class CacheNode {
		int key;
		int value;
		int frequency;
		public CacheNode(int key, int value, int frequency) {
			this.key  = key;
			this.value = value;
			this.frequency = frequency;
		}
	}
	//Store all cache nodes
	Map<Integer, CacheNode> entries;
	//List of all frequencies
	Map<Integer, LinkedHashSet> frequencyMap;
	//Book keeping variables
	int capacity; //maximum capacity of the whole class
	int minFrequency; //minimum frequency of all nodes
	int maxFrequency; //maximum frequency of all nodes


	public LFUCache(int capacity) {
		this.capacity = capacity;
		entries = new HashMap<>();
		frequencyMap =  new HashMap<>();
		minFrequency = 1;
		maxFrequency = 1;   
	}

	public int get(int key) {
		// System.out.println("getting "+key);
    	//BUGWARNING: capacity == 0 need to count?
		if (!entries.containsKey(key) || capacity == 0) {
        	//not found, return 
			return -1;
		}
		increaseRank(key);
		return entries.get(key).value;
	}

	public void put(int key, int value) {
		// System.out.println("putting "+key + " with value "+value);
    	//BUGWARNING: capacity == 0, nothing should be done
		if (capacity == 0) {
			return;
		}
		if (entries.containsKey(key)) {
			entries.get(key).value = value;
			increaseRank(key);
		} else {
			if (entries.size() == capacity) {
				removeLFU();
			}
			CacheNode node = new CacheNode(key,value,1);
			insertNode(node);
		}

	}
//=========================== PRIVACY WARNING ++++++++++++++++++++++++++//

	void increaseRank(int key) {
		CacheNode node = entries.get(key);
		int frequency = node.frequency;
	//frequency is about to be incremented by 1
		int newFrequency = ++node.frequency;
		maxFrequency = Math.max(maxFrequency,newFrequency);
		if (!frequencyMap.containsKey(newFrequency)) {
			frequencyMap.put(newFrequency,new LinkedHashSet<>());
		}
	//Remove the node from the current list and add to the new list
		LinkedHashSet curSet = frequencyMap.get(frequency);
		LinkedHashSet newSet = frequencyMap.get(newFrequency);

		curSet.remove(node);
		newSet.add(node);
		if(frequency == minFrequency && curSet.isEmpty()) {
			updateMinFrequency();
		}
	}

	void removeLFU() {
		LinkedHashSet set = frequencyMap.get(minFrequency);
		if(set == null) {
		//nothing is there, return
			return;
		}
	//IMPORTANT: how to traverse through the linked hash map
		Iterator<CacheNode> it = set.iterator();
		CacheNode victim = it.next();
		set.remove(victim);
		entries.remove(victim.key);
		updateMinFrequency();
	}

	void insertNode(CacheNode node) {
		int frequency = node.frequency;
		minFrequency = Math.min(minFrequency,frequency);
		if(!frequencyMap.containsKey(frequency)) {
			frequencyMap.put(frequency,new LinkedHashSet<>());
		}
		frequencyMap.get(frequency).add(node);
		entries.put(node.key,node);
	}

	void updateMinFrequency() {
		while(minFrequency <= maxFrequency ) {
		// System.out.println("minFrequency is "+ minFrequency + "maxFrequency is "+ maxFrequency);
			if (frequencyMap.containsKey(minFrequency) && !frequencyMap.get(minFrequency).isEmpty()) {
				break;
			}
			minFrequency++;
		}
		if (minFrequency > maxFrequency) {
		//nothing is found, set to 1
		// System.out.println("minFrequency is "+ minFrequency + "maxFrequency is "+ maxFrequency);
			minFrequency = 1;
			maxFrequency = 1;
		}
	}

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */