class Solution {
    public String maskPII(String s) {
        int at = s.indexOf('@');

        if (at != -1) {
            String lower = s.toLowerCase();
            return lower.charAt(0) + "*****" + lower.charAt(at - 1) + lower.substring(at);
        }

        StringBuilder digits = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }

        String local = "***-***-" + digits.substring(digits.length() - 4);
        int countryLength = digits.length() - 10;

        if (countryLength == 0) {
            return local;
        }

        return "+" + "*".repeat(countryLength) + "-" + local;
    }
}