class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int q = queries.length;
        int[] ans = new int[q];
        Arrays.fill(ans, -1);

        List<List<int[]>> deferred = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            deferred.add(new ArrayList<>());
        }

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                deferred.get(b).add(new int[]{heights[a], i});
            }
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));

        for (int i = 0; i < n; i++) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] < heights[i]) {
                ans[minHeap.poll()[1]] = i;
            }
            for (int[] query : deferred.get(i)) {
                minHeap.offer(query);
            }
        }

        return ans;
    }
}