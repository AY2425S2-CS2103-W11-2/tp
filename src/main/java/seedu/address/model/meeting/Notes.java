package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's notes in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNotes(String)}
 */
public class Notes {
    public final String value;

    /**
     * Constructs an {@code Notes}.
     *
     * @param notes Any string value
     */
    public Notes(String notes) {
        requireNonNull(notes);
        value = notes;
    }

    /**
     * Returns true if a given string is a valid posiiton.
     */
    public static boolean isValidNotes(String test) {
        return true;
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
        if (!(other instanceof Notes)) {
            return false;
        }

        Notes otherCompany = (Notes) other;
        return value.equals(otherCompany.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
