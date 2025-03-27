package seedu.address.logic.parser;

import java.util.Comparator;

/**
 * A comparator for sorting priority values in a specific order: "Low", "Medium", "High".
 * This comparator assigns a numerical ranking to each priority level and compares them accordingly.
 *
 * The ranking is as follows:
 * <ul>
 *   <li>"Low" -> 1</li>
 *   <li>"Medium" -> 2</li>
 *   <li>"High" -> 3</li>
 * </ul>
 *
 * Any unexpected or unknown priority values are assigned a default rank of 0.
 *
 * This comparator can be used to sort priority strings in either ascending or descending order,
 * depending on whether it is used directly or in combination with {@code Comparator.reversed()}.
 *
 * Example:
 * <pre>
 * List&lt;String&gt; priorities = Arrays.asList("Medium", "High", "Low");
 * priorities.sort(new SortComparator()); // Sorts as: "Low", "Medium", "High"
 * </pre>
 *
 * Implements {@code Comparator<String>}.
 */
public class SortComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int rank1 = getRank(o1);
        int rank2 = getRank(o2);

        return Integer.compare(rank1, rank2);
    }

    private int getRank(Object o) {
        return switch (o.toString()) {
        case "low" -> 1;
        case "medium" -> 2;
        case "high" -> 3;
        default -> 0;
        };
    }
}
