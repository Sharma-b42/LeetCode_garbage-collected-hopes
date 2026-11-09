class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);

        while (result.size() < n) {
            List<Integer> next = new ArrayList<>();

            for (int num : result) {
                if (2 * num - 1 <= n) {
                    next.add(2 * num - 1);
                }
            }
            for (int num : result) {
                if (2 * num <= n) {
                    next.add(2 * num);
                }
            }

            result = next;
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}