class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] indexedEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            indexedEdges[i][0] = edges[i][0];
            indexedEdges[i][1] = edges[i][1];
            indexedEdges[i][2] = edges[i][2];
            indexedEdges[i][3] = i;
        }

        Arrays.sort(indexedEdges, (a, b) -> Integer.compare(a[2], b[2]));

        int baseWeight = kruskal(n, indexedEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (kruskal(n, indexedEdges, i, -1) > baseWeight) {
                critical.add(indexedEdges[i][3]);
            } else if (kruskal(n, indexedEdges, -1, i) == baseWeight) {
                pseudoCritical.add(indexedEdges[i][3]);
            }
        }

        List<List<Integer>> result = new ArrayList<>(2);
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int kruskal(int n, int[][] edges, int skipIdx, int forceIdx) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int weight = 0;
        int components = n;

        if (forceIdx != -1) {
            int[] e = edges[forceIdx];
            if (union(parent, size, e[0], e[1])) {
                weight += e[2];
                components--;
            }
        }

        for (int i = 0; i < edges.length; i++) {
            if (i == skipIdx || i == forceIdx) {
                continue;
            }
            int[] e = edges[i];
            if (union(parent, size, e[0], e[1])) {
                weight += e[2];
                components--;
                if (components == 1) {
                    break;
                }
            }
        }

        return components == 1 ? weight : Integer.MAX_VALUE;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private boolean union(int[] parent, int[] size, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA == rootB) {
            return false;
        }
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
        return true;
    }
}