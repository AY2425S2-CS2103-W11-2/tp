package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    // Fields
    private final MeetingTime meetingTime;
    private final Set<String> people = new HashSet<>();
    private final String notes;


    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingTime meetingTime, Set<String> people, String notes) {
        requireAllNonNull(meetingTime, people, notes);
        this.meetingTime = meetingTime;
        this.people.addAll(people);
        this.notes = notes;
    }

    public MeetingTime getDateTime() {
        return meetingTime;
    }
    
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getPersonList() {
        return Collections.unmodifiableSet(people);
    }

    public String getNotes() {
        return notes;
    }

    /**
     * Returns true if both meetings have the same meetingTime and people.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return meetingTime.equals(otherMeeting.meetingTime)
                && people.equals(otherMeeting.people);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingTime, people, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("meetingTime", meetingTime)
                .add("people", people)
                .add("notes", notes)
                .toString();
    }


}
