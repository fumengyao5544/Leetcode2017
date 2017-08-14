public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return result;
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings){
            // left point
            points.add(new int[]{building[0], -building[2]});
            // right point
            points.add(new int[]{building[1], building[2]});
        }
        Collections.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                if (point1[0] != point2[0]) {
                    return point1[0] - point2[0];
                }else{
                    return point1[1] - point2[1];
                }
            }
        });
        // maxHeap
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        heap.offer(0);
        int max = 0;
        for (int i = 0; i < points.size(); i++) {
            int[] point = points.get(i);
            if (point[1] < 0){
                heap.offer(-point[1]);
            }else {
                heap.remove(point[1]);
            }
            if (max != heap.peek()){
                result.add(new int[]{point[0], heap.peek()});
                max = heap.peek();
            }
        }
        return result;
    }
}
