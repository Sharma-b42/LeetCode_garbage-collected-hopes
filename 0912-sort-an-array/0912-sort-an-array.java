class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(nums, i, n);
        }

        for (int end = n - 1; end > 0; end--) {
            int temp = nums[0];
            nums[0] = nums[end];
            nums[end] = temp;
            siftDown(nums, 0, end);
        }

        return nums;
    }

    private void siftDown(int[] nums, int i, int size) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && nums[left] > nums[largest]) {
                largest = left;
            }
            if (right < size && nums[right] > nums[largest]) {
                largest = right;
            }
            if (largest == i) {
                return;
            }

            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            i = largest;
        }
    }
}