class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        int n = products.length;
        int start = 0;
        StringBuilder prefix = new StringBuilder();

        for (char c : searchWord.toCharArray()) {
            prefix.append(c);
            String current = prefix.toString();
            start = lowerBound(products, current, start);

            List<String> suggestions = new ArrayList<>();
            for (int i = start; i < Math.min(start + 3, n); i++) {
                if (products[i].startsWith(current)) {
                    suggestions.add(products[i]);
                } else {
                    break;
                }
            }
            result.add(suggestions);
        }

        return result;
    }

    private int lowerBound(String[] products, String target, int from) {
        int low = from;
        int high = products.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (products[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}