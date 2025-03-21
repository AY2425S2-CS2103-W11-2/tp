package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "first second";

        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(firstPredicateKeywordList, "");
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondPredicateKeywordList, "");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList, "");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                "Alice", "");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate("Alice Bob",
                "");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate("Bob Carol",
                "");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate("aLIce bOB",
                "");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        PersonContainsKeywordsPredicate predicate;

        // Non-matching keyword
        predicate = new PersonContainsKeywordsPredicate("Carol", "");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PersonContainsKeywordsPredicate("12345 alice@email.com Main Street",
                "");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withCompany("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        String keywords = "keyword1 keyword2";
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords,
                "");

        String expected = PersonContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
