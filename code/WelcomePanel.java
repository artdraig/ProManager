import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WelcomePanel extends JPanel {

    static JTextArea welcome_text;
    static JButton new_project_button;
    static JButton open_project_button;

    WelcomePanel() {
        new_project_button = new JButton("New Project");
        new_project_button.addActionListener(
            (e) -> {
                ProManager.SwitchLayout("New Project");
            }
        );
        new_project_button.setFont(ProManager.default_font);

        open_project_button = new JButton("Open Project");
        open_project_button.addActionListener(
            (e) -> {
                this.add(new JButton("Button"));
                this.revalidate();
                this.repaint();
            }
        );
        open_project_button.setFont(ProManager.default_font);

        welcome_text = new JTextArea("Welcome to ProManager");
        welcome_text.setEditable(false);
        welcome_text.setFont(ProManager.title_font);

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.anchor = GridBagConstraints.SOUTH;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weighty=0.2;
        this.add(welcome_text, constraint);

        constraint.anchor = GridBagConstraints.CENTER;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx=0;
        this.add(new_project_button, constraint);

        constraint.gridx = 2;
        constraint.gridy = 1;
        this.add(open_project_button, constraint);
    }
}
