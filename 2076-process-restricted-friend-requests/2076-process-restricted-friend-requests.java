class Solution {
    private int[] parent;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int rootU = find(requests[i][0]);
            int rootV = find(requests[i][1]);

            if (rootU == rootV) {
                result[i] = true;
                continue;
            }

            boolean allowed = true;
            for (int[] r : restrictions) {
                int rootX = find(r[0]);
                int rootY = find(r[1]);

                if ((rootX == rootU && rootY == rootV) || (rootX == rootV && rootY == rootU)) {
                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                parent[rootU] = rootV;
                result[i] = true;
            }
        }

        return result;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}