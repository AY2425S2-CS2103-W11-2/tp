package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integrationn tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand("first", "");
        FindCommand findSecondCommand = new FindCommand("second", "");

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand("first", "");
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommand command = prepareFindCommand("alsdkfjklasdjfl");
        expectedModel.updateFilteredPersonList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    //    @Test
    //    public void execute_multipleKeywords_multiplePersonsFound() {
    //        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //        FindCommand command = prepareFindCommand("Kurz Elle Kunz");
    //        expectedModel.updateFilteredPersonList(command.getPredicate());
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    //    }

    @Test
    public void toStringMethod() {
        FindCommand findCommand = new FindCommand("keyword", "");
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findCommand.getPredicate() + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand}.
     * */
    private FindCommand prepareFindCommand(String userInput) {
        String trimmedArgs = userInput.trim();

        StringBuilder nameKeyword = new StringBuilder();
        StringBuilder companyKeyword = new StringBuilder();

        //Split The Input By Spaces
        String[] tokens = trimmedArgs.split("\\s+");
        boolean isCompanyMode = false;

        for (String token : tokens) {
            if (token.equalsIgnoreCase("/company")) {
                isCompanyMode = true;
            } else {
                if (isCompanyMode) {
                    companyKeyword.append(token);
                    companyKeyword.append(" ");
                } else {
                    nameKeyword.append(token);
                    nameKeyword.append(" ");
                }
            }
        }

        return new FindCommand(nameKeyword.toString().trim(), companyKeyword.toString().trim());
    }
}
