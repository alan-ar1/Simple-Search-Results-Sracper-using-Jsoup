
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Scraper {

    ArrayList<String> titleResults = new ArrayList<>();
    ArrayList<String> linkResults = new ArrayList<>();

    SearchResults sr;
    String searchEngine;
    String searchedURL;

    Scraper(String searchEngine, SearchResults sr) {
        this.searchEngine = searchEngine;
        this.sr = sr;

        this.searchedURL = this.searchEngine + SearchPanel.searchField.getText();

    }

    public void scrap(String elementsClasses, String searchTitleElement, String searchLinkElement) {

        try {

            Document doc = Jsoup.connect(this.searchedURL).get();

            Elements searchResults = doc.select(elementsClasses);

            for (Element result : searchResults) {

                Element titleElement = result.select(searchTitleElement).first();
                Element linkElement = result.select(searchLinkElement).first();

                if (titleElement == null || linkElement == null) {
                    continue;
                }

                try {

                    String title = titleElement.text();
                    titleResults.add(title);

                    String link;

                    if (searchLinkElement.equals("a")) {
                        link = linkElement.attr("href");
                    } else {
                        link = linkElement.text();
                    }
                    linkResults.add(link);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "A problem while extracting data: " + e.getMessage(),
                            "Extraction Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (titleResults.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No Search Results", "Search Results", JOptionPane.WARNING_MESSAGE);
                return;
            }

            sr.addingToPanel(titleResults, linkResults);

        }

        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Please check your internet connection.",
                    "Internet Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
