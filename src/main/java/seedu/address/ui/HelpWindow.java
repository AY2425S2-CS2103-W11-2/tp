package seedu.address.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.sandec.mdfx.MarkdownView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL =
        "https://github.com/AY2425S2-CS2103-W11-2/tp/blob/master/docs/UserGuide.md";
    public static final String HELP_MESSAGE =
        "Use the 'help' command or refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        File file = new File(System.getProperty("user.dir") + "/docs/UserGuide.md");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("User guide has moved??");
        }
        String line = "";
        String output = "";
        boolean inHelpSection = false;
        Pattern p = Pattern.compile("!?\\[.*?\\]\\((.*?)\\)");
        while (sc != null && sc.hasNextLine()) {
            line = sc.nextLine();
            // check
            if (line.contains("## Features")) {
                // this is a start of command section
                inHelpSection = true;
            }
            if (line.contains("## Usage information")) {
                // this is the next section header
                break;
            }
            if (inHelpSection) {
                // check if the line contains an image embed
                if (!p.matcher(line).find()) {
                    // add the line to the output
                    output += line + "\n";
                }
            }
        }

        MarkdownView mdfx = new MarkdownView(output);
        mdfx.prefWidthProperty().bind(scrollPane.widthProperty());
        mdfx.prefHeightProperty().bind(scrollPane.heightProperty());
        mdfx.setStyle("-fx-background-color: white; -fx-padding: 10px;");
        scrollPane.setContent(mdfx);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
