package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATETIME, PREFIX_NOTES, PREFIX_PERSONS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATETIME, PREFIX_NOTES);

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();

        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editMeetingDescriptor.setMeetingTime(ParserUtil.parseMeetingTime(
                    argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTES).isPresent()) {
            editMeetingDescriptor.setNotes(ParserUtil.parseNotes(argMultimap.getValue(PREFIX_NOTES).get()));
        }
        parsePersonsForEdit(argMultimap.getAllValues(PREFIX_PERSONS)).ifPresent(editMeetingDescriptor::setPeople);

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

    /**
     * Parses {@code Collection<String> persons} into a {@code Set<Person>} if {@code persons} is non-empty.
     * If {@code persons} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Person>} containing zero tags.
     */
    private Optional<Set<String>> parsePersonsForEdit(Collection<String> persons) throws ParseException {
        System.out.println("parsePersonsForEdit " + persons);
        assert persons != null;

        if (persons.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> personSet = persons.size() == 1 && persons.contains("") ? Collections.emptySet() : persons;
        System.out.println("parsePersonsForEdit person set " + personSet);
        return Optional.of(ParserUtil.parsePersonList(personSet));
    }

}
