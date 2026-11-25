class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 265_443_576L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0;
        long h2 = 0;
        long pow1 = 1;
        long pow2 = 1;

        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        int capacity = ((n - len + 1) * 4) / 3 + 4;
        Map<Long, Integer> freq = new HashMap<>(capacity);
        freq.put((h1 << 32) | h2, 1);

        for (int i = len; i < n; i++) {
            h1 = (h1 - (nums[i - len] * pow1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + nums[i]) % MOD1;

            h2 = (h2 - (nums[i - len] * pow2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;

            long key = (h1 << 32) | h2;
            freq.merge(key, 1, Integer::sum);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}