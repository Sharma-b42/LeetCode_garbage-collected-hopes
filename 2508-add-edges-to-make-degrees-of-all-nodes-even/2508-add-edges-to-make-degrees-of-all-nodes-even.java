class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new HashSet<>());
        }

        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (adj.get(i).size() % 2 == 1) {
                odd.add(i);
            }
        }

        if (odd.isEmpty()) {
            return true;
        }

        if (odd.size() == 2) {
            int a = odd.get(0);
            int b = odd.get(1);

            if (!adj.get(a).contains(b)) {
                return true;
            }

            for (int c = 1; c <= n; c++) {
                if (c != a && c != b && !adj.get(c).contains(a) && !adj.get(c).contains(b)) {
                    return true;
                }
            }

            return false;
        }

        if (odd.size() == 4) {
            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            return (!adj.get(a).contains(b) && !adj.get(c).contains(d))
                || (!adj.get(a).contains(c) && !adj.get(b).contains(d))
                || (!adj.get(a).contains(d) && !adj.get(b).contains(c));
        }

        return false;
    }
}