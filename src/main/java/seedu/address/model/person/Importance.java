package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Importance {

    public static final String MESSAGE_CONSTRAINTS = "Importance should only contain one of the following: \n"
            + "LOW or MEDIUM or HIGH";

    /*
     * The first character of the Importance Field must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Importance}.
     *
     * @param importance A valid importance field.
     */
    public Importance(String importance) {
        requireNonNull(importance);
        checkArgument(isValidImportance(importance), MESSAGE_CONSTRAINTS);
        value = importance.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidImportance(String test) {
        return test.matches(VALIDATION_REGEX);
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
