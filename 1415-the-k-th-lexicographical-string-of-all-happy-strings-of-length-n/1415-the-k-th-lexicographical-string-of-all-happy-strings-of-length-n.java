class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));
        if (k > total) {
            return "";
        }

        int perBranch = total / 3;
        StringBuilder sb = new StringBuilder();
        char prev = 0;
        k--;

        for (int pos = 0; pos < n; pos++) {
            for (char c = 'a'; c <= 'c'; c++) {
                if (c == prev) {
                    continue;
                }

                if (k < perBranch) {
                    sb.append(c);
                    prev = c;
                    break;
                }
                k -= perBranch;
            }

            if (pos < n - 1) {
                perBranch /= 2;
            }
        }

        return sb.toString();
    }
}