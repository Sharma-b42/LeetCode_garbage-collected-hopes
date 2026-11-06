class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int totalEven = 0;
        int totalOdd = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEven += nums[i];
            } else {
                totalOdd += nums[i];
            }
        }

        int leftEven = 0;
        int leftOdd = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int rightEven;
            int rightOdd;

            if (i % 2 == 0) {
                rightEven = totalEven - leftEven - nums[i];
                rightOdd = totalOdd - leftOdd;
            } else {
                rightOdd = totalOdd - leftOdd - nums[i];
                rightEven = totalEven - leftEven;
            }

            if (leftEven + rightOdd == leftOdd + rightEven) {
                count++;
            }

            if (i % 2 == 0) {
                leftEven += nums[i];
            } else {
                leftOdd += nums[i];
            }
        }

        return count;
    }
}