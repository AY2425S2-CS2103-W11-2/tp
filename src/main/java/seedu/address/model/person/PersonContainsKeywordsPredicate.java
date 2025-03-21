package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final String nameKeyword;
    private final String companyKeyword;

    /**
     * Constructs a {@code PersonContainsKeywordsPredicate} with the specified name and company keywords.
     * The predicate will match a {@code Person} if either the person's name contains any of the specified
     * name keywords or if the person's company contains any of the specified company keywords.
     * Both name and company searches are case-insensitive.
     *
     * @param keywords a list of name keywords to search for in a person's name
     * @param companyKeyword a list of company keywords to search for in a person's company name
     */
    public PersonContainsKeywordsPredicate(String keywords, String companyKeyword) {
        this.nameKeyword = keywords;
        this.companyKeyword = companyKeyword;
    }

    @Override
    public boolean test(Person person) {
        boolean matchesName = !nameKeyword.isEmpty()
                && StringUtil.containsIgnoreCase(person.getName().fullName, nameKeyword);

        if (companyKeyword.isEmpty()) {
            return matchesName;
        } else {
            boolean matchesCompany = StringUtil.containsIgnoreCase(person.getCompany().toString(), companyKeyword);
            return matchesName && matchesCompany;
        }
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
        return nameKeyword.equals(otherPersonContainsKeywordsPredicate.nameKeyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", nameKeyword).toString();
    }
}
