package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting times should only contain numeric characters, slashes, dashes, colons and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=.*[0-9])[\\d/\\-:\\s]+$";

    public final String meetingTime;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param inputTime A valid time.
     */
    public MeetingTime(String inputTime) {
        requireNonNull(inputTime);
        checkArgument(isValidMeetingTime(inputTime), MESSAGE_CONSTRAINTS);
        meetingTime = inputTime;
    }

    /**
     * Returns true if a given string is a valid meeting time.
     */
    public static boolean isValidMeetingTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return meetingTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingTime)) {
            return false;
        }

        MeetingTime otherTime = (MeetingTime) other;
        return meetingTime.equals(otherTime.meetingTime);
    }

    @Override
    public int hashCode() {
        return meetingTime.hashCode();
    }

}
