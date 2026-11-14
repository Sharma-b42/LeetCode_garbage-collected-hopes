class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int best = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            for (int len = l; len <= r && start + len <= n; len++) {
                int sum = prefix[start + len] - prefix[start];
                if (sum > 0) {
                    best = Math.min(best, sum);
                }
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}