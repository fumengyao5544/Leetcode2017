// 1. 找出最短路径，变化次数最少: BFS

// 需要记录得到每个词从start 最少变化了几次（距离），当变化结果为end 时结束
// 每个词对应一个它要start 的距离，想到用 HashMap.
// HashMap<String, Integer> distance
// 用 queue 实现BFS：
// • start 放入字典里
// • start 放入queue
// • start 放入distance map 里， （start, 0）
// • 一个boolean 变量foundEnd 表示是否变到了end, foundEnd 初始为false
// • 每层遍历queue 的长度， 每poll 出一个词， 从distance map 里得到这个词到start 的
// 距离currDistance， 调用getNeighbors 生成所有下一次变化可能产生的词
// • 对每个下一次变化可能产生的词， 看distance map 是否存在， 如果不存在把它放进
// distance map， 距离为currDistance + 1. 如果distance map 里存在,不需进行任何操作
// • 同时对每个下一次变化可能产生的词， 看这个词是否为end，如果不是把它放到
// queue 中，如果是foundEnd = true, queue 遍历到这一层为止

// 2. 输出最短路径上的所有单词: DFS + backtracking

// 要输出最短路径上的所有单词，就要能从每一个单词找到它变化后的下一层的单词，需要对每
// 个单词和它的下一层单词建立一一映射，想到用HashMap.
// HashMap<String, ArrayList<String>> nodeNeighbors
// 每个string 作为key， value 为一个list 记录所有key 一次可以产生的且在字典中的单词
// 在上面BFS 找最短路径更新distance map 时同时更新nodeNeighbors map
// 从start 开始找end，对每个词curr 从 nodeNeighbors Map 中找它的下一层，再分别找出下一层
// 的单词next 和当前词curr 在 distance Map 中的距离, 如果next 比curr 的距离大一则说明next
// 为一下层的词. 一以next 为新的起点递归调用，当next 为end 时停止


public class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
        HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
        ArrayList<String> solution = new ArrayList<String>();

        dict.add(start);
        
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict){
            nodeNeighbors.put(str, new ArrayList<String>());
        }
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor)){// Found the shortest path
                            foundEnd = true;
                        }else{
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            if (foundEnd) break;
        }
    }
    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }
}
