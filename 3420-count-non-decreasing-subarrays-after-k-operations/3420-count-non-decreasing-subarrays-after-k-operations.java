class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        long cost = 0;
        long result = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (!deque.isEmpty() && nums[deque.peekFirst()] <= nums[left]) {
                int idx = deque.pollFirst();
                int end = deque.isEmpty() ? right + 1 : deque.peekFirst();
                cost += (long) (nums[left] - nums[idx]) * (end - idx);
            }
            deque.offerFirst(left);

            while (cost > k) {
                int back = deque.peekLast();
                cost -= nums[back] - nums[right];
                if (back == right) {
                    deque.pollLast();
                }
                right--;
            }

            result += right - left + 1;
        }

        return result;
    }
}