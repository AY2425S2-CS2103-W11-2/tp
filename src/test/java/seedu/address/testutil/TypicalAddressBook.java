package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAddressBook {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withCompany("Telegram").withPosition("Software Engineer")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withCompany("Microsoft").withPosition("Product Manager")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com")
            .withCompany("Tesla").withPosition("Mechanical Engineer").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withCompany("Amazon").withPosition("Data Analyst")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com")
            .withCompany("Meta").withPosition("UI/UX Designer").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com")
            .withCompany("Netflix").withPosition("Marketing Specialist").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com")
            .withCompany("Apple").withPosition("Hardware Engineer").build();

    //meetings
    public static final Meeting MEETING1 = new MeetingBuilder().withMeetingTime("2025-12-30 14:00")
            .withPersons("Benson Meier", "Alice Pauline", "Daniel Meier")
            .withNotes("Discuss about the new project").build();
    public static final Meeting MEETING2 = new MeetingBuilder().withMeetingTime("2025-12-15 15:20")
            .withPersons("Daniel Meier").withNotes("Interview for summer internship").build();
    public static final Meeting MEETING3 = new MeetingBuilder().withMeetingTime("2025-02-13 10:00")
            .withPersons("Daniel Meier", "George Best")
            .withNotes("Consultation at Singapore Coding Conference").build();
    public static final Meeting MEETING4 = new MeetingBuilder().withMeetingTime("2025-08-21 09:00")
            .withPersons("Fiona Kunz", "George Best").withNotes("").build();
    public static final Meeting MEETING5 = new MeetingBuilder().withMeetingTime("2025-08-30 09:00")
            .withPersons("Fiona Kunz").withNotes("").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com")
            .withCompany("Oracle").withPosition("Database Administrator").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com")
            .withCompany("IBM").withPosition("Cybersecurity Analyst").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withCompany(VALID_COMPANY_AMY).withPosition(VALID_POSITION_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withCompany(VALID_COMPANY_BOB)
            .withPosition(VALID_POSITION_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting, ab.getPersonList());
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING1, MEETING2, MEETING3, MEETING4, MEETING5));
    }
}
