class Solution {
    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] road : roads) {
            int rootA = find(parent, road[0]);
            int rootB = find(parent, road[1]);
            if (rootA != rootB) {
                parent[rootA] = rootB;
            }
        }

        int target = find(parent, 1);
        int min = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (find(parent, road[0]) == target) {
                min = Math.min(min, road[2]);
            }
        }

        return min;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}