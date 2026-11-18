class Solution {
    private int best = 0;
    private int[] values;
    private List<int[]>[] graph;
    private int[] visitCount;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        this.values = values;
        graph = new List[n];
        visitCount = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        visitCount[0] = 1;
        dfs(0, maxTime, values[0]);

        return best;
    }

    private void dfs(int node, int timeLeft, int quality) {
        if (node == 0) {
            best = Math.max(best, quality);
        }

        for (int[] next : graph[node]) {
            int neighbor = next[0];
            int cost = next[1];

            if (cost > timeLeft) {
                continue;
            }

            int gain = visitCount[neighbor] == 0 ? values[neighbor] : 0;
            visitCount[neighbor]++;
            dfs(neighbor, timeLeft - cost, quality + gain);
            visitCount[neighbor]--;
        }
    }
}