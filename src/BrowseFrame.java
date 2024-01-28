import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseFrame extends JFrame implements ActionListener {

    static JPanel mainPanel;
    static SearchResults searchResults;

    BrowseFrame() {

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setTitle("Web Scraping Search Engines Results");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(Ui.color));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1000, 1000));
        mainPanel.setBackground(null);

        searchResults = new SearchResults();

        mainPanel.add(new SearchPanel(this), BorderLayout.NORTH);

        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(100, 50));
        footer.setBackground(null);

        mainPanel.add(footer, BorderLayout.SOUTH);

        this.add(mainPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Ui.color == Ui.mainColor) {
            Ui.toggleMode(Ui.secondaryColor, this);
        } else {
            Ui.toggleMode(Ui.mainColor, this);
        }
        this.repaint();
    }

}
