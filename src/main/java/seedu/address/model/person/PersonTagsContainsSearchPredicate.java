package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;


/**
 * Tests that at least one of a {@code Person}'s {@code tags} contains the given searchTerm.
 */
public class PersonTagsContainsSearchPredicate implements Predicate<Person> {
    private final String searchTerm;

    /**
     * Constructs a {@code PersonTagsContainsSearchPredicate} with the specified searchTerm.
     * The predicate will match a {@code Person} if the person's list of tags contains the specified search term.
     * Searches are case-insensitive.
     *
     * @param searchTerm the term to search for in a person's list of tags
     */
    public PersonTagsContainsSearchPredicate(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean test(Person person) {

        if (searchTerm.isEmpty()) {
            return false;
        } else {
            boolean matchesSearch = false;
            for (Tag tag: person.getTags()) {
                matchesSearch = StringUtil.containsIgnoreCase(tag.tagName, searchTerm);
                if (matchesSearch) {
                    break;
                }
            }
            return matchesSearch;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonTagsContainsSearchPredicate)) {
            return false;
        }

        PersonTagsContainsSearchPredicate otherPersonTagsContainsSearchPredicate =
                (PersonTagsContainsSearchPredicate) other;
        return searchTerm.equals(otherPersonTagsContainsSearchPredicate.searchTerm);
    }

    @Override
    public String toString() {
        return searchTerm;
    }
}
