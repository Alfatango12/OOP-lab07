package it.unibo.nestedenum;

import java.time.Month;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private void checkMonthName(final String name) {
        boolean isEqual = false;
        for (final Month m : Month.values()) {
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
                final boolean leapYear = false; // For this implementation, the leapYear does not modify the days-order of the months
                checkMonthName(s1);
                checkMonthName(s2);

                return Integer.compare(Month.valueOf(s1.toUpperCase()).length(leapYear), Month.valueOf(s2.toUpperCase()).length(leapYear));
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

                return Integer.compare(Month.valueOf(s1.toUpperCase()).getValue(), Month.valueOf(s2.toUpperCase()).getValue());
            }
        };
    }

    
}
