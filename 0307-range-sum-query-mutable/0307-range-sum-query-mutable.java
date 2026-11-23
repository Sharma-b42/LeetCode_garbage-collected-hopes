class NumArray {
    private final int[] nums;
    private final int[] tree;
    private final int n;

    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = new int[n];
        this.tree = new int[n + 1];

        for (int i = 0; i < n; i++) {
            this.nums[i] = nums[i];
            this.tree[i + 1] = nums[i];
        }

        for (int i = 1; i <= n; i++) {
            int parent = i + (i & -i);
            if (parent <= n) {
                tree[parent] += tree[i];
            }
        }
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;

        for (int i = index + 1; i <= n; i += i & -i) {
            tree[i] += diff;
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    private int prefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */