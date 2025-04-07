package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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

    private static final DateTimeFormatter[] ACCEPTED_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
    };
    private static final String[] FRIENDLY_FORMATS = {
        "yyyy-MM-dd HH:mm (e.g. 2024-04-01 14:30)",
        "dd/MM/yyyy HH:mm (e.g. 01/04/2024 14:30)",
        "MM/dd/yyyy HH:mm (e.g. 04/01/2024 14:30)"
    };

    public final LocalDateTime meetingTime;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param inputTime A valid time.
     */
    public MeetingTime(String inputTime) {
        requireNonNull(inputTime);
        this.meetingTime = parseDateTime(inputTime);
    }

    private static LocalDateTime parseDateTime(String inputTime) {
        for (int i = 0; i < ACCEPTED_FORMATS.length; i++) {
            DateTimeFormatter formatter = ACCEPTED_FORMATS[i];
            try {
                LocalDateTime parsed = LocalDateTime.parse(inputTime, formatter);
                String reformatted = parsed.format(formatter);
                if (!reformatted.equals(inputTime)) {
                    throw new IllegalArgumentException("Input \"" + inputTime + "\" is not in the correct format: "
                            + FRIENDLY_FORMATS[i]);
                }
                return parsed;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Input \"" + inputTime + "\" does not match any accepted date format");
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
