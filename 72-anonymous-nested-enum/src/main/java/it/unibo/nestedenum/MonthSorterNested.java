package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private enum Months {
        january,
        february,
        march,
        april,
        may,
        june,
        july,
        august,
        september,
        october,
        november,
        december
    }

    private void checkMonthName(final String name) {
        final String normalizedName = name.toLowerCase();
        boolean isEqual = false;
        for (final Months month : MonthSorterNested.Months.values()) {
            if (normalizedName.equals(month.toString())) {
                isEqual = true;
                break;
            }
        }

        if (!isEqual) {
            throw new IllegalArgumentException("The months " + name + " does not exists");
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
