import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ui {

    static int mainColor = 0x18181b;
    static int secondaryColor = 0xcbd5e1;
    static int color = mainColor;

    static int txtColor = 0x475569;
    static Font titleFont = new Font("Arial", Font.BOLD, 20);
    static Font linkFont = new Font("Arial", Font.ITALIC, 20);

    public static void toggleMode(int c, JFrame f) {
        color = c;
        f.getContentPane().setBackground(new Color(Ui.color));
    }

    public static JButton createStyledButton(String text) {

        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        Font buttonFont = new Font("Arial", Font.BOLD, 17);
        button.setFont(buttonFont);
        button.setPreferredSize(new Dimension(150, 40));

        button.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }

            @Override

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(52, 152, 219));
            }
        });

        button.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 2));

        return button;
    }

    public static BasicScrollBarUI createScrollBar() {

        return new BasicScrollBarUI() {
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                g.setColor(new Color(0x3f3f46));
                g.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
            }
        };
    }
}
