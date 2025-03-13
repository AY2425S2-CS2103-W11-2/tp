package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
//        PersonContainsKeywordsPredicate firstPredicate =
//                new PersonContainsKeywordsPredicate(Collections.singletonList("first"), Collections.emptyList());
//        PersonContainsKeywordsPredicate secondPredicate =
//                new PersonContainsKeywordsPredicate(Collections.singletonList("second"), Collections.emptyList());

        FindCommand findFirstCommand = new FindCommand(Collections.singletonList("first"), Collections.emptyList());
        FindCommand findSecondCommand = new FindCommand(Collections.singletonList("second"), Collections.emptyList());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(Collections.singletonList("first"), Collections.emptyList());
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommand command = prepareFindCommand("");
        expectedModel.updateFilteredPersonList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command = prepareFindCommand("Kurz Elle Kunz");
        expectedModel.updateFilteredPersonList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        FindCommand findCommand = new FindCommand(List.of("keyword"), List.of());
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findCommand.getPredicate() + "}";
        assertEquals(expected, findCommand.toString());
    }

//    /**
//     * Parses {@code userInput} into a {@code PersonContainsKeywordsPredicate}.
//     */
//    private PersonContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new PersonContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }

    /**
     * Parses {@code userInput} into a {@code FindCommand}.
     * */
    private FindCommand prepareFindCommand(String userInput) {
        List<String> nameKeywords = List.of();
        List<String> companyKeywords = List.of();

        if (userInput.startsWith("/company")) {
            companyKeywords = Arrays.asList(userInput.replace("/company", "").trim().split("\\s+"));
        } else {
            nameKeywords = Arrays.asList(userInput.split("\\s+"));
        }

        return new FindCommand(nameKeywords, companyKeywords);
    }
}
