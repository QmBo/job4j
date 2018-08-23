package ru.job4j.bank;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Bank
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 23.08.2018
 */
public class Bank {
    /**
     * Visit Class.
     */
    public static class Visit {
        private final long in;
        private final long out;

        public Visit(final long in, final long out) {
            this.in = in;
            this.out = out;
        }
    }

    /**
     * Info class.
     */
    public static class Info {
        private long max;
        private long start;
        private long end;

        public Info() {
            this(0, 0, 0);
        }

        public Info(long max, long start, long end) {
            this.max = max;
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            if (max != info.max) {
                return false;
            }
            if (start != info.start) {
                return false;
            }
            return end == info.end;
        }

        @Override
        public int hashCode() {
            int result = (int) (max ^ (max >>> 32));
            result = 31 * result + (int) (start ^ (start >>> 32));
            result = 31 * result + (int) (end ^ (end >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "max=" + max
                    + ", start=" + this.toTime(this.start)
                    + ", end=" + this.toTime(this.end)
                    + '}';
        }

        public String toTime(long time) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            return String.format("%s:%s", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
        }
    }

    /**
     * Return list of maximum visit in time range.
     * @param visits visits list.
     * @return list of maximum visit in time range.
     */
    public List<Info> max(List<Visit> visits) {
        List<Info> periods = new ArrayList<>();
        List<Visit> checkList = new ArrayList<>(visits);
        if (visits.size() > 0) {
            long in = checkList.get(0).in;
            long out = checkList.remove(0).out;
            int max = 1;
            for (Visit visit : checkList) {
                if (inRange(in, out, visit.in)) {
                    max++;
                    if (inRange(in, out, visit.out)) {
                        out = visit.out;
                    }
                    in = visit.in;
                } else if (inRange(in, out, visit.out)) {
                    max++;
                    out = visit.out;
                }
            }
            periods.add(new Info(max, in, out));
        }
        return periods;
    }

    /**
     * Check value in left to right range.
     * @param left left.
     * @param right right.
     * @param value value.
     * @return value in left to right range.
     */
    private boolean inRange(long left, long right, long value) {
        return value >= left && value <= right;
    }
}