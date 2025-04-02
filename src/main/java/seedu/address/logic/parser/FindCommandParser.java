package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY);

        String nameKeyword = argMultimap.getPreamble().trim();
        String companyKeyword = argMultimap.getValue(PREFIX_COMPANY).orElse("");

        nameKeyword = nameKeyword.replaceAll("\\s+", " ").trim();

        if (nameKeyword.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getAllValues(PREFIX_COMPANY).isEmpty() && companyKeyword.equalsIgnoreCase("")) {
            throw new ParseException("Company Field cannot be empty");
        }

        return new FindCommand(nameKeyword.trim(), companyKeyword.trim());
    }

}
