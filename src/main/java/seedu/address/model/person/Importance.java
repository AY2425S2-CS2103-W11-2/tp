package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Importance in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidImportance(String)}
 */
public class Importance {

    public static final String MESSAGE_CONSTRAINTS = "Importance should only contain one of the following: \n"
            + "Low or Medium or High (Case-Insensitive) \n";

    /*
     * The first character of the Importance Field must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String[] VALID_IMPORTANCE_VALUES = {"low", "medium", "high"};

    public final String value;

    /**
     * Constructs an {@code Importance}.
     *
     * @param importance A valid importance field.
     */
    public Importance(String importance) {
        requireNonNull(importance);
        checkArgument(isValidImportance(importance), MESSAGE_CONSTRAINTS);
        value = capitalizeFirst(importance.toLowerCase());
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidImportance(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String normalizedImportance = test.trim().toLowerCase();
        for (String validImportance : VALID_IMPORTANCE_VALUES) {
            if (validImportance.equalsIgnoreCase(normalizedImportance)) {
                return true;
            }
        }
        return false;
    }

    private static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Importance)) {
            return false;
        }

        Importance otherImportance = (Importance) other;
        return value.equals(otherImportance.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
