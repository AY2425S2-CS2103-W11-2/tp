package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


import java.util.Comparator;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by %s in %s order.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of students in the address book and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: FIELD (name, age, etc.) ORDER (asc/desc)\n"
            + "Example: " + COMMAND_WORD + " priority asc";

    private final Comparator<Person> comparator;
    private final String field;
    private final String order;


    public SortCommand(Comparator<Person> comparator, String field, String order) {
        this.comparator = comparator;
        this.field = field;
        this.order = order;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredPersonList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, field, order));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && field.equals(((SortCommand) other).field)
                && order.equals(((SortCommand) other).order));
    }
}
