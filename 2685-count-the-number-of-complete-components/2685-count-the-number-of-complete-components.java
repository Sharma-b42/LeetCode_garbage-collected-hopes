class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        int[] edgeCount = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);

            if (rootU != rootV) {
                if (size[rootU] < size[rootV]) {
                    int temp = rootU;
                    rootU = rootV;
                    rootV = temp;
                }
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
                edgeCount[rootU] += edgeCount[rootV] + 1;
            } else {
                edgeCount[rootU]++;
            }
        }

        int completeCount = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i && edgeCount[i] == size[i] * (size[i] - 1) / 2) {
                completeCount++;
            }
        }

        return completeCount;
    }

    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }
}