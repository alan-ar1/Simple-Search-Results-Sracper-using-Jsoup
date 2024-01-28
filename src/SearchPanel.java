import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    static String[] searchEnginesNames = { "Google", "Duck Duck Go", "Bing", "Yahoo" };
    static JTextField searchField;
    static JButton searchButton;
    static JComboBox selectSearchEngine;
    static JButton toggle;

    SearchPanel(BrowseFrame mainFrame) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 40));
        searchField.setFont(Ui.titleFont);
        searchButton = Ui.createStyledButton("Search");
        searchButton.addActionListener(BrowseFrame.searchResults);
        selectSearchEngine = new JComboBox(this.searchEnginesNames);
        selectSearchEngine.setPreferredSize(new Dimension(150, 40));
        selectSearchEngine.setSelectedIndex(0);
        selectSearchEngine.setFont(Ui.titleFont);

        toggle = Ui.createStyledButton("Toggle Mode");
        toggle.addActionListener(mainFrame);

        this.add(searchField);
        this.add(selectSearchEngine);
        this.add(searchButton);
        this.add(toggle);
        this.setBackground(null);

    }
}
