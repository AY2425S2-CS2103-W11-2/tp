package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_MEETINGTIME = "2025-12-30 14:00";
    public static final Set<String> DEFAULT_PERSONS = new HashSet<>(Arrays.asList("Alice"));
    public static final String DEFAULT_NOTES = "Discuss about project";

    private MeetingTime meetingTime;
    private Set<String> persons;
    private String notes;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        meetingTime = new MeetingTime(DEFAULT_MEETINGTIME);
        persons = new HashSet<>(DEFAULT_PERSONS);
        notes = DEFAULT_NOTES;
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        meetingTime = meetingToCopy.getDateTime();
        persons = new HashSet<>(meetingToCopy.getPersonList());
        notes = meetingToCopy.getNotes();
    }

    /**
     * Sets the {@code MeetingTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withMeetingTime(String meetingTime) {
        this.meetingTime = new MeetingTime(meetingTime);
        return this;
    }

    /**
     * Parses the {@code persons} into a {@code Set<String>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPersons(String ... persons) {
        this.persons = SampleDataUtil.getPersonSet(persons);
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Meeting build() {
        return new Meeting(meetingTime, persons, notes);
    }

}
