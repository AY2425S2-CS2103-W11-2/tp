package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Notes;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Importance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807"),
                    new Company("Google"), new Position("Software Engineer"),
                    getTagSet("friends"), new Importance("HIGH")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758"),
                    new Company("Microsoft"), new Position("Product Manager"),
                    getTagSet("colleagues", "friends"), new Importance("MEDIUM")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283"),
                    new Company("Facebook"), new Position("UX Designer"),
                    getTagSet("neighbours"), new Importance("LOW")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282"),
                    new Company("Apple"), new Position("Data Analyst"),
                    getTagSet("family"), new Importance("LOW")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021"),
                    new Company("Amazon"), new Position("Marketing Specialist"),
                    getTagSet("classmates"), new Importance("MEDIUM")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417"),
                    new Company("Tesla"), new Position("Mechanical Engineer"),
                    getTagSet("colleagues"), new Importance("HIGH"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(new MeetingTime("2025-12-30 14:00"), getPersonSet("Bernice Yu", "Alex Yeoh", "David Li"),
                        new Notes("Discuss about the new project")),
            new Meeting(new MeetingTime("2025-12-15 15:20"), getPersonSet("David Li"),
                        new Notes("Interview for summer internship")),
            new Meeting(new MeetingTime("2025-02-13 10:00"), getPersonSet("David Li", "Roy Balakrishnan"),
                        new Notes("Consultation at Singapore Coding Conference"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleAb.addMeeting(sampleMeeting, sampleAb.getPersonList());
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a person set containing the list of name strings given.
     */
    public static Set<String> getPersonSet(String... strings) {
        return Arrays.stream(strings)
                .collect(Collectors.toSet());
    }

}
