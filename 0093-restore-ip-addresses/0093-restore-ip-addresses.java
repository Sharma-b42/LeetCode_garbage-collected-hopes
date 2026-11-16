class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> parts, List<String> result) {
        int remainingParts = 4 - parts.size();
        int remainingChars = s.length() - start;

        if (remainingChars < remainingParts || remainingChars > remainingParts * 3) {
            return;
        }

        if (parts.size() == 4) {
            result.add(String.join(".", parts));
            return;
        }

        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            String part = s.substring(start, start + len);

            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }
            if (Integer.parseInt(part) > 255) {
                break;
            }

            parts.add(part);
            backtrack(s, start + len, parts, result);
            parts.remove(parts.size() - 1);
        }
    }
}