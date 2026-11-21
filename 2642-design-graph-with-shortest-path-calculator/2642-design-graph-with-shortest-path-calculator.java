class Graph {
    private final int n;
    private final List<int[]>[] adj;

    public Graph(int n, int[][] edges) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(int[] edge) {
        adj[edge[0]].add(new int[]{edge[1], edge[2]});
    }

    public int shortestPath(int node1, int node2) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[node1] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        heap.offer(new int[]{node1, 0});

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int node = curr[0];

            if (node == node2) {
                return curr[1];
            }
            if (curr[1] > dist[node]) {
                continue;
            }

            for (int[] next : adj[node]) {
                int newDist = curr[1] + next[1];
                if (newDist < dist[next[0]]) {
                    dist[next[0]] = newDist;
                    heap.offer(new int[]{next[0], newDist});
                }
            }
        }

        return -1;
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */