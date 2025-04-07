package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private Label id;
    @FXML
    private HBox cardPane;
    @FXML
    private Label meetingTime;
    @FXML
    private FlowPane people;
    @FXML
    private Label notes;

    /**
     * Creates a {@code MeetingCode} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        assert(displayedIndex >= 0);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        meetingTime.setText(meeting.getDateTime().toString());
        notes.setText(meeting.getNotes().value);
        meeting.getPersonList().stream().forEach(person ->
        {
            Label personLabel = new Label(person);
            personLabel.setMaxWidth(300);
            personLabel.setMinHeight(Region.USE_PREF_SIZE);
            personLabel.setStyle("-fx-text-overflow: ellipsis;");
            people.getChildren().add(personLabel);
        });
    }
}
