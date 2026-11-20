class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        visited[id] = true;

        for (int depth = 0; depth < level; depth++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int person = queue.poll();
                for (int friend : friends[person]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        for (int person : queue) {
            for (String video : watchedVideos.get(person)) {
                freq.merge(video, 1, Integer::sum);
            }
        }

        List<String> result = new ArrayList<>(freq.keySet());
        result.sort((a, b) -> freq.get(a).equals(freq.get(b)) ? a.compareTo(b) : freq.get(a) - freq.get(b));

        return result;
    }
}