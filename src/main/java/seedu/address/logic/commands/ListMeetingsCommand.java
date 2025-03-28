package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * List all meetings in the address book to the user
 */
public class ListMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "meetings";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
