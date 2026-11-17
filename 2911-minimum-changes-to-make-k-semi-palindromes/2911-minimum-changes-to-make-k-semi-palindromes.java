class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = semiCost(s, i, j);
            }
        }

        final int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int j = 2 * p; j <= n; j++) {
                for (int i = 2 * (p - 1); i <= j - 2; i++) {
                    if (dp[p - 1][i] < INF) {
                        dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i][j - 1]);
                    }
                }
            }
        }

        return dp[k][n];
    }

    private int semiCost(String s, int i, int j) {
        int len = j - i + 1;
        int best = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }

            int changes = 0;
            for (int g = 0; g < d; g++) {
                int lo = i + g;
                int hi = j - d + 1 + g;
                while (lo < hi) {
                    if (s.charAt(lo) != s.charAt(hi)) {
                        changes++;
                    }
                    lo += d;
                    hi -= d;
                }
            }
            best = Math.min(best, changes);
        }

        return best;
    }
}