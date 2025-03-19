package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        StringBuilder nameKeyword = new StringBuilder();
        List<String> companyKeywords = new ArrayList<>();

        //Split The Input By Spaces
        String[] tokens = trimmedArgs.split("\\s+");
        boolean isCompanyMode = false;

        for (String token : tokens) {
            if (token.equalsIgnoreCase("/company")) {
                isCompanyMode = true;
            } else {
                if (isCompanyMode) {
                    companyKeywords.add(token);
                } else {
                    nameKeyword.append(token);
                    nameKeyword.append(" ");
                }
            }
        }

        return new FindCommand(nameKeyword.toString().trim(), companyKeywords);
    }

}
