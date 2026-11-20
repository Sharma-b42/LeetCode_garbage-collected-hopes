
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();

        for (int routeIdx = 0; routeIdx < n; routeIdx++) {
            for (int stop : routes[routeIdx]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(routeIdx);
            }
        }

        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visitedRoute = new boolean[n];

        for (int routeIdx : stopToRoutes.get(source)) {
            queue.offer(routeIdx);
            visitedRoute[routeIdx] = true;
        }
        stopToRoutes.remove(source);

        int buses = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentRoute = queue.poll();

                for (int stop : routes[currentRoute]) {
                    if (stop == target) {
                        return buses;
                    }

                    List<Integer> connectedRoutes = stopToRoutes.remove(stop);
                    if (connectedRoutes != null) {
                        for (int nextRoute : connectedRoutes) {
                            if (!visitedRoute[nextRoute]) {
                                visitedRoute[nextRoute] = true;
                                queue.offer(nextRoute);
                            }
                        }
                    }
                }
            }

            buses++;
        }

        return -1;
    }
}