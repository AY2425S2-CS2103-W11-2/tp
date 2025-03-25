package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATETIME, PREFIX_PERSONS, PREFIX_NOTES);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATETIME, PREFIX_PERSONS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATETIME, PREFIX_NOTES);
        MeetingTime dateTime = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_DATETIME).get());
        Set<String> personList = ParserUtil.parsePersonList(argMultimap.getAllValues(PREFIX_PERSONS));
        String notes = "";
        System.out.println(argMultimap.getValue(PREFIX_NOTES).isPresent());
        System.out.println(argMultimap.getValue(PREFIX_NOTES));
        if (argMultimap.getValue(PREFIX_NOTES).isPresent()) {
            notes = argMultimap.getValue(PREFIX_NOTES).get().trim();
        }

        Meeting meeting = new Meeting(dateTime, personList, notes);

        return new AddMeetingCommand(meeting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        Stream.of(prefixes).forEach(prefix ->
                System.out.println(prefix + String.valueOf(argumentMultimap.getValue(prefix).isPresent())));
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
