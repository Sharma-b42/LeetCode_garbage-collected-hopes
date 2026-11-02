class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        int upper = 0;

        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                upper++;
            }
        }

        return upper == 0 || upper == n || (upper == 1 && Character.isUpperCase(word.charAt(0)));
    }
}