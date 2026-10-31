class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ops = new ArrayList<>();
        int stream = 1;

        for (int num : target) {
            while (stream < num) {
                ops.add("Push");
                ops.add("Pop");
                stream++;
            }
            ops.add("Push");
            stream++;
        }

        return ops;
    }
}