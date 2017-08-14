public class RandomizedSet {
    private HashMap<Integer, Integer> locations;
    private ArrayList<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
       locations = new HashMap<>();
       list = new ArrayList<>(); 
       random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(locations.containsKey(val)) {
            return false;
        }
        list.add(val);
        locations.put(val, list.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!locations.containsKey(val)) {
            return false;
        }
        int location = locations.get(val);
        //This if statment is a common source of bug
        if (location != list.size() -1) {
            int  tailValue = (list.get(list.size()-1));
            list.set(location, tailValue);
            locations.put(tailValue,location);

        }
        list.remove(list.size() -1);
        locations.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
       if(list.isEmpty()) {
        return -1;
       }
       int index = random.nextInt(list.size());
       return list.get(index); 
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */