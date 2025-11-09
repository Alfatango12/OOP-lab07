package it.unibo.nestedenum;

import java.time.Month;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final int SHORT_MONTH = 28;
    private static final int USUAL_MONTH = 30;
    private static final int LONG_MONTH = 31;

    private void checkMonthName(final String name) {
        boolean isEqual = false;
        for (final Months m : Months.values()) {
            if (name.toLowerCase().equals(m.toString().toLowerCase())) {
                isEqual = true;
                break;
            }
        }

        if (!isEqual) {
            throw new IllegalArgumentException("The month " + name + " does not exists");
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                checkMonthName(s1);
                checkMonthName(s2);

                return Integer.compare(Months.valueOf(s1.toUpperCase()).getDays(), Months.valueOf(s2.toUpperCase()).getDays());
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                checkMonthName(s1);
                checkMonthName(s2);

                return s1.compareTo(s2);
            }
        };
    }

    public enum Months {
        JANUARY(LONG_MONTH), 
        FEBRUARY(SHORT_MONTH), 
        MARCH(LONG_MONTH), 
        APRIL(USUAL_MONTH), 
        MAY(LONG_MONTH),
        JUNE(USUAL_MONTH),
        JULY(LONG_MONTH),
        AUGUST(LONG_MONTH),
        SEPTEMBER(USUAL_MONTH),
        OCTOBER(LONG_MONTH),
        NOVEMBER(USUAL_MONTH),
        DECEMBER(LONG_MONTH);

        private final int days;
        Months (int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }
}
