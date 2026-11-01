class Solution {
    public boolean isPossible(int[] target) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;

        for (int num : target) {
            maxHeap.offer((long) num);
            sum += num;
        }

        while (maxHeap.peek() > 1) {
            long largest = maxHeap.poll();
            long rest = sum - largest;

            if (rest == 1) {
                return true;
            }
            if (rest == 0 || largest <= rest) {
                return false;
            }

            long previous = largest % rest;
            if (previous == 0) {
                return false;
            }

            maxHeap.offer(previous);
            sum = rest + previous;
        }

        return true;
    }
}