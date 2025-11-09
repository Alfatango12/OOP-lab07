package it.unibo.nestedenum;

import java.time.Month;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private final static int monthNotAmbiguousOccurrences = 1;

    private void checkMonthName(final String name) {
        int counter = 0;
        for (final Month m : Month.values()) {
            
            if (m.toString().toLowerCase().startsWith(name.toLowerCase())) {
                counter++;
            }   
        }
        if (counter != monthNotAmbiguousOccurrences) {
            throw new IllegalArgumentException("This name is not a valid month");
        }
    }

    private String getMonthFromAbbreviation(final String name) {
        String foundName = "";
        for (final Month m : Month.values()) {
            
            if (m.toString().toLowerCase().startsWith(name.toLowerCase())) {
                foundName = m.toString();
                break;
            }   
        }

        return foundName;
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                final boolean leapYear = false; // For this implementation, the leapYear does not modify the days-order of the months
                checkMonthName(s1);
                checkMonthName(s2);

                String fullName1 = getMonthFromAbbreviation(s1);
                String fullName2 = getMonthFromAbbreviation(s2);

                return Integer.compare(Month.valueOf(fullName1.toUpperCase()).length(leapYear), Month.valueOf(fullName2.toUpperCase()).length(leapYear));
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

                String fullName1 = getMonthFromAbbreviation(s1);
                String fullName2 = getMonthFromAbbreviation(s2);

                return Integer.compare(Month.valueOf(fullName1.toUpperCase()).getValue(), Month.valueOf(fullName2.toUpperCase()).getValue());
            }
        };
    }

    
}
