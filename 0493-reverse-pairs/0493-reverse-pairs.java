class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    private int mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid, temp) + mergeSort(nums, mid + 1, right, temp);

        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            count += j - (mid + 1);
        }

        int i = left;
        j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, left, nums, left, right - left + 1);

        return count;
    }
}