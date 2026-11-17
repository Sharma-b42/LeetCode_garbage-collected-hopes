class Solution {
    private int[] parent;

    public int largestComponentSize(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        parent = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            parent[i] = i;
        }

        for (int num : nums) {
            for (int f = 2; (long) f * f <= num; f++) {
                if (num % f == 0) {
                    union(num, f);
                    union(num, num / f);
                }
            }
        }

        Map<Integer, Integer> size = new HashMap<>();
        int result = 0;

        for (int num : nums) {
            int root = find(num);
            int count = size.merge(root, 1, Integer::sum);
            result = Math.max(result, count);
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

    private void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}