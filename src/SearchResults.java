import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

public class SearchResults implements ActionListener {
    static JScrollPane scrollPane;

    ArrayList<JButton> clickButtons = new ArrayList<>();
    ArrayList<String> clickLinks = new ArrayList<>();

    String[] parentElementClasses = { ".g", ".result", "li.b_algo", "li" };
    String[] searchTitleElements = { ".LC20lb", "a" };
    String[] searchLinkElements = { "a", ".result__url", "cite", ".compTitle h3.title a" };
    String[] searchEnginesWebsiteLink = { "https://www.google.com/search?q=", "https://www.duckduckgo.com/html?q=",
            "https://www.bing.com/search?q=", "https://search.yahoo.com/search?p=" };

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == SearchPanel.searchButton) {
            if (SearchPanel.searchField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "You didn't input anything", "Search Results",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (scrollPane != null) {
                BrowseFrame.mainPanel.remove(scrollPane);

                BrowseFrame.mainPanel.repaint();
            }
            if (SearchPanel.selectSearchEngine.getSelectedItem() == SearchPanel.searchEnginesNames[0]) {

                new Scraper(searchEnginesWebsiteLink[0], this).scrap(parentElementClasses[0], searchTitleElements[0],
                        searchLinkElements[0]);

            } else if (SearchPanel.selectSearchEngine.getSelectedItem() == SearchPanel.searchEnginesNames[1]) {
                new Scraper(searchEnginesWebsiteLink[1], this).scrap(parentElementClasses[1], searchTitleElements[1],
                        searchLinkElements[1]);
            } else if (SearchPanel.selectSearchEngine.getSelectedItem() == SearchPanel.searchEnginesNames[2]) {
                new Scraper(searchEnginesWebsiteLink[2], this).scrap(parentElementClasses[2], searchTitleElements[1],
                        searchLinkElements[2]);
            } else {
                new Scraper(searchEnginesWebsiteLink[3], this).scrap(parentElementClasses[3], searchTitleElements[1],
                        searchLinkElements[3]);
            }
            return;
        }

        for (int i = 0; i < clickButtons.size(); i++) {
            if (clickButtons.get(i) == e.getSource()) {
                openBrowse(clickLinks.get(i));
            }
        }
    }

    private void openBrowse(String url) {
        try {

            Desktop.getDesktop().browse(new URI(url));
        }

        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Browser ", "Search Results", JOptionPane.WARNING_MESSAGE);

        }
    }

    public void addingToPanel(ArrayList<String> titleResults, ArrayList<String> linkResults) {

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(titleResults.size(), 1));
        container.setBackground(null);

        this.clickLinks.clear();
        this.clickButtons.clear();

        for (int i = 0; i < titleResults.size(); i++) {

            JPanel resultPanel = new JPanel(new GridLayout(3, 1));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
            buttonPanel.setBackground(null);

            JLabel titleLabel = new JLabel(titleResults.get(i));
            JLabel linkLabel = new JLabel(linkResults.get(i));
            titleLabel.setFont(Ui.titleFont);
            titleLabel.setForeground(new Color(Ui.txtColor));
            linkLabel.setFont(Ui.linkFont);
            linkLabel.setForeground(new Color(Ui.txtColor));

            this.clickButtons.add(Ui.createStyledButton("click"));
            this.clickButtons.get(i).addActionListener(this);
            this.clickLinks.add(linkResults.get(i));

            buttonPanel.add(clickButtons.get(i));
            resultPanel.add(titleLabel);
            resultPanel.add(linkLabel);
            resultPanel.add(buttonPanel);

            resultPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));
            resultPanel.setBackground(null);

            container.add(resultPanel);
        }

        scrollPane = new JScrollPane(container);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));

        scrollPane.getHorizontalScrollBar().setUI(Ui.createScrollBar());
        scrollPane.getVerticalScrollBar().setUI(Ui.createScrollBar());
        scrollPane.setBackground(null);

        BrowseFrame.mainPanel.add(scrollPane);

        BrowseFrame.mainPanel.revalidate();
        BrowseFrame.mainPanel.repaint();

    }
}
