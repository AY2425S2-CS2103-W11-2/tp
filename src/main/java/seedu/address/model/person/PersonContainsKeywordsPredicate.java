package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> companyKeywords;

    public PersonContainsKeywordsPredicate(List<String> keywords, List<String> companyKeywords) {
        this.nameKeywords = keywords;
        this.companyKeywords = companyKeywords;
    }

    @Override
    public boolean test(Person person) {
//        return nameKeywords.stream()
//                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        boolean matchesName = nameKeywords.isEmpty() || nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getName().fullName, keyword));

        //TODO Add the Company Boolean Value once PR is merged
        return matchesName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate otherPersonContainsKeywordsPredicate = (PersonContainsKeywordsPredicate) other;
        return nameKeywords.equals(otherPersonContainsKeywordsPredicate.nameKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", nameKeywords).toString();
    }
}
