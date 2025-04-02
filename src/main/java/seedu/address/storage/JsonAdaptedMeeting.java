package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Notes;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String meetingTime;
    private final List<String> persons = new ArrayList<>();
    private final String notes;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("meetingTime") String meetingTime,
                             @JsonProperty("persons") List<String> persons,
                             @JsonProperty("notes") String notes) {
        this.meetingTime = meetingTime;
        this.persons.addAll(persons);
        this.notes = notes;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        meetingTime = source.getDateTime().toString();
        persons.addAll(source.getPersonList());
        notes = source.getNotes().value;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<String> meetingPersons = new ArrayList<>();
        for (String person : persons) {
            meetingPersons.add(person);
        }

        if (meetingTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "MeetingTime"));
        }
        if (!MeetingTime.isValidMeetingTime(meetingTime)) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        final MeetingTime modelMeetingTime = new MeetingTime(meetingTime);

        if (persons == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Persons"));
        }
        final Set<String> modelPersons = new HashSet<>(meetingPersons);

        final Notes modelNotes = new Notes(notes);

        return new Meeting(modelMeetingTime, modelPersons, modelNotes);
    }

}
