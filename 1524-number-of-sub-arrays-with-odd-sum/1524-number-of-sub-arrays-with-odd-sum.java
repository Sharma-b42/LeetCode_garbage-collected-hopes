class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long odd = 0;
        long even = 1;
        int prefix = 0;
        long result = 0;

        for (int num : arr) {
            prefix = (prefix + num) % 2;

            if (prefix == 1) {
                result += even;
                odd++;
            } else {
                result += odd;
                even++;
            }
        }

        return (int) (result % MOD);
    }
}