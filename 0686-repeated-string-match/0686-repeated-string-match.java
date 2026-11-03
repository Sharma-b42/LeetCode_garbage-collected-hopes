class Solution {
    public int repeatedStringMatch(String a, String b) {
        int count = (b.length() + a.length() - 1) / a.length();
        StringBuilder sb = new StringBuilder(a.repeat(count));

        if (sb.indexOf(b) != -1) {
            return count;
        }

        sb.append(a);
        if (sb.indexOf(b) != -1) {
            return count + 1;
        }

        return -1;
    }
}