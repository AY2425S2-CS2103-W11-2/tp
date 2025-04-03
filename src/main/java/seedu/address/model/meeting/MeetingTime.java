package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeetingTime(String)}
 */
public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting time must be in one of the following formats:\n"
                    + "1. yyyy-MM-dd HH:mm (e.g., 2024-04-01 14:30)\n"
                    + "2. dd/MM/yyyy HH:mm (e.g., 01/04/2024 14:30)\n"
                    + "3. MM/dd/yyyy HH:mm (e.g., 04/01/2024 14:30)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=.*[0-9])[\\d/\\-:\\s]+$";

    public final LocalDateTime meetingTime;

    private static final DateTimeFormatter[] ACCEPTED_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
    };

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param inputTime A valid time.
     */
    public MeetingTime(String inputTime) {
        requireNonNull(inputTime);
        checkArgument(isValidMeetingTime(inputTime), MESSAGE_CONSTRAINTS);
        this.meetingTime = parseDateTime(inputTime);
    }

    private static LocalDateTime parseDateTime(String inputTime) {
        for (DateTimeFormatter formatter : ACCEPTED_FORMATS) {
            try {
                return LocalDateTime.parse(inputTime, formatter);
            } catch (DateTimeParseException ignored) {

            }
        }
        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks if a given string is a valid meeting time format.
     *
     * @param test The string to check.
     * @return True if valid, false otherwise.
     */
    public static boolean isValidMeetingTime(String test) {
        try {
            parseDateTime(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return meetingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
