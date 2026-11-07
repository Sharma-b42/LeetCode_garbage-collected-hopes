class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);

        int operations = 0;
        int level = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                level++;
            }
            operations += level;
        }

        return operations;
    }
}