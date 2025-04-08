package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Notes;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATETIME + "DATETIME] "
            + "[" + PREFIX_PERSONS + "CONTACT_NAME] "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATETIME + "03-12-2025 12:00 "
            + PREFIX_PERSONS + "Alice "
            + PREFIX_PERSONS + "Bob "
            + PREFIX_NOTES + "Settle admin matters";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";
    public static final String MESSAGE_EMPTY_PERSONS = "Meeting requires at least one person";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editMeetingDescriptor details to edit the person with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX_NONE);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            if (lastShownList.size() == 1) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX_SINGLE);
            }
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX,
                lastShownList.size()));
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        if (editedMeeting.getPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_PERSONS);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedMeeting)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        MeetingTime updatedDt = editMeetingDescriptor.getMeetingTime().orElse(meetingToEdit.getDateTime());
        Notes updatedNotes = editMeetingDescriptor.getNotes().orElse(meetingToEdit.getNotes());
        Set<String> updatedPeople = editMeetingDescriptor.getPeople().orElse(meetingToEdit.getPersonList());
        return new Meeting(updatedDt, updatedPeople, updatedNotes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        EditMeetingCommand otherEditCommand = (EditMeetingCommand) other;
        return index.equals(otherEditCommand.index)
                && editMeetingDescriptor.equals(otherEditCommand.editMeetingDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editMeetingDescriptor", editMeetingDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditMeetingDescriptor {
        private MeetingTime meetingTime;
        private Notes notes;
        private Set<String> people;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setMeetingTime(toCopy.meetingTime);
            setNotes(toCopy.notes);
            setPeople(toCopy.people);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(meetingTime, notes, people);
        }

        public void setMeetingTime(MeetingTime meetingTime) {
            this.meetingTime = meetingTime;
        }

        public Optional<MeetingTime> getMeetingTime() {
            return Optional.ofNullable(meetingTime);
        }

        public void setNotes(Notes notes) {
            this.notes = notes;
        }

        public Optional<Notes> getNotes() {
            return Optional.ofNullable(notes);
        }

        /**
         * Sets {@code people} to this object's {@code people}.
         * A defensive copy of {@code people} is used internally.
         */
        public void setPeople(Set<String> people) {
            this.people = (people != null) ? new HashSet<>(people) : null;
        }

        /**
         * Returns an unmodifiable people set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code people} is null.
         */
        public Optional<Set<String>> getPeople() {
            return (people != null) ? Optional.of(Collections.unmodifiableSet(people)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            EditMeetingDescriptor otherEditMeetingDescriptor = (EditMeetingDescriptor) other;
            return Objects.equals(meetingTime, otherEditMeetingDescriptor.meetingTime)
                    && Objects.equals(notes, otherEditMeetingDescriptor.notes)
                    && Objects.equals(people, otherEditMeetingDescriptor.people);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("meeting time", meetingTime)
                    .add("notes", notes)
                    .add("people", people)
                    .toString();
        }
    }
}
