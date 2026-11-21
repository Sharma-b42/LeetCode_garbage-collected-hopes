class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] saved = new int[n];
        int[] idx = new int[n];
        int[] order = new int[n];
        int count = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        saved[0] = last[s.charAt(0) - 'a'];
        last[s.charAt(0) - 'a'] = 0;
        order[count++] = 0;
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.peek();

            if (idx[node] < children[node].size()) {
                int child = children[node].get(idx[node]++);
                int c = s.charAt(child) - 'a';

                if (last[c] != -1) {
                    newParent[child] = last[c];
                }

                saved[child] = last[c];
                last[c] = child;
                order[count++] = child;
                stack.push(child);
            } else {
                stack.pop();
                last[s.charAt(node) - 'a'] = saved[node];
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i >= 1; i--) {
            int node = order[i];
            size[newParent[node]] += size[node];
        }

        return size;
    }
}