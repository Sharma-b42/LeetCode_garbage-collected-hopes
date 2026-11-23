import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {
    private final List<int[]> bookings;
    private final List<int[]> overlaps;

    public MyCalendarTwo() {
        this.bookings = new ArrayList<>();
        this.overlaps = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        for (int[] overlap : overlaps) {
            if (startTime < overlap[1] && overlap[0] < endTime) {
                return false;
            }
        }

        for (int[] booking : bookings) {
            if (startTime < booking[1] && booking[0] < endTime) {
                overlaps.add(new int[]{
                    Math.max(startTime, booking[0]),
                    Math.min(endTime, booking[1])
                });
            }
        }

        bookings.add(new int[]{startTime, endTime});
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */