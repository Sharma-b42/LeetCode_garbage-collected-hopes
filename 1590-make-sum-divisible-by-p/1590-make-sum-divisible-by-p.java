class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0;
        for (int num : nums) {
            total += num;
        }

        int need = (int) (total % p);
        if (need == 0) {
            return 0;
        }

        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, -1);
        int minLen = n;
        long prefix = 0;

        for (int i = 0; i < n; i++) {
            prefix += nums[i];
            int cur = (int) (prefix % p);
            int target = (cur - need + p) % p;

            if (lastIndex.containsKey(target)) {
                minLen = Math.min(minLen, i - lastIndex.get(target));
            }
            lastIndex.put(cur, i);
        }

        return minLen == n ? -1 : minLen;
    }
}