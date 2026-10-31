class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int prev = 0;

        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            boolean isStart = parts[1].equals("start");
            int time = Integer.parseInt(parts[2]);

            if (isStart) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - prev;
                }
                stack.push(id);
                prev = time;
            } else {
                result[stack.pop()] += time - prev + 1;
                prev = time + 1;
            }
        }

        return result;
    }
}