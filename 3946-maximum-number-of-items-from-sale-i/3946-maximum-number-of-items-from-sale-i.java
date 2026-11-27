class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] multiples = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] > 0) {
                for (int m = f; m <= maxFactor; m += f) {
                    multiples[f] += freq[m];
                }
            }
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int[] item : items) {
            int price = item[1];
            int gain = multiples[item[0]];
            for (int b = budget; b >= price; b--) {
                if (dp[b - price] != -1) {
                    dp[b] = Math.max(dp[b], dp[b - price] + gain);
                }
            }
        }

        int maxCopies = 0;
        for (int b = 0; b <= budget; b++) {
            if (dp[b] != -1) {
                maxCopies = Math.max(maxCopies, dp[b] + (budget - b) / minPrice);
            }
        }

        return maxCopies;
    }
}