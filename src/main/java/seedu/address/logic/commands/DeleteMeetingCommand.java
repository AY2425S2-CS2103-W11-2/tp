package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Deletes a meeting identified using it's displayed index from the address book.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletemeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting identified by the index number used in the displayed meeting list.\n"
            + "Parameters: INDEX (must be a positive integer smaller than the size of the meeting list)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted meeting: %1$s";

    private final Index targetIndex;

    public DeleteMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX_NONE);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            if (lastShownList.size() == 1) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX_SINGLE);
            }
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX,
                lastShownList.size()));
        }

        Meeting meetingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteMeeting(meetingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, Messages.format(meetingToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteMeetingCommand otherDeleteMeetingCommand = (DeleteMeetingCommand) other;
        return targetIndex.equals(otherDeleteMeetingCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
