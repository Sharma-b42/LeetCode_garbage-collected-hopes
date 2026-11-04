class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        Comparator<Integer> cmp = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> lo = new TreeSet<>(cmp), hi = new TreeSet<>(cmp);
        long loSum = 0, hiSum = 0;
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            if (lo.isEmpty() || cmp.compare(i, lo.last()) < 0) {
                lo.add(i);
                loSum += nums[i];
            } else {
                hi.add(i);
                hiSum += nums[i];
            }

            if (i >= x) {
                int old = i - x;
                if (lo.remove(old)) {
                    loSum -= nums[old];
                } else {
                    hi.remove(old);
                    hiSum -= nums[old];
                }
            }

            int want = (Math.min(i + 1, x) + 1) / 2;
            while (lo.size() > want) {
                int m = lo.pollLast();
                loSum -= nums[m];
                hiSum += nums[m];
                hi.add(m);
            }
            while (lo.size() < want) {
                int m = hi.pollFirst();
                hiSum -= nums[m];
                loSum += nums[m];
                lo.add(m);
            }

            if (i >= x - 1) {
                long med = nums[lo.last()];
                cost[i - x + 1] = med * lo.size() - loSum + hiSum - med * hi.size();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);
            for (int i = x; i <= n; i++) {
                curr[i] = Math.min(curr[i - 1], prev[i - x] + cost[i - x]);
            }
            prev = curr;
        }

        return prev[n];
    }
}