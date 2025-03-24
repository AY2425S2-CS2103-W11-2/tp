package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

import java.util.Comparator;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SortCommandParser implements Parser<SortCommand> {

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
