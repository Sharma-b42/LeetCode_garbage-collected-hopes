class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int total = 1;
        int up = 0;
        int down = 0;
        int peak = 0;

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                up++;
                peak = up;
                down = 0;
                total += 1 + up;
            } else if (ratings[i] == ratings[i - 1]) {
                up = 0;
                down = 0;
                peak = 0;
                total += 1;
            } else {
                up = 0;
                down++;
                total += down + (peak >= down ? 0 : 1);
            }
        }

        return total;
    }
}