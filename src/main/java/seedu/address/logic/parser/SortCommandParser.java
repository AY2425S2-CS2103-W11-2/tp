package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String[] splitArgs = trimmedArgs.split("\\s+");
        if (splitArgs.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String field = splitArgs[0];
        String order = splitArgs[1];

        Comparator<Person> comparator = switch (field) {
        case "name" -> Comparator.comparing(Person::getName, Comparator.naturalOrder());
        case "importance" -> Comparator.comparing(Person::getImportance, new SortComparator());
        default -> throw new ParseException("Invalid field: " + field);
        };

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        } else if (!"asc".equalsIgnoreCase(order)) {
            throw new ParseException("Invalid order: " + order);
        }
        return new SortCommand(comparator, field, order);
    }
}
