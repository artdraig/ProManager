import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ProManager {

    public static Font title_font = new Font("Utopia", Font.PLAIN, 54);
    public static Font default_font = new Font("Utopia", Font.PLAIN, 36);
    public static Color cornflower_blue = new Color(100, 149, 237);
    public static Border field_border;

    public static JFrame window;
    public static JPanel card_panel;
    public static WelcomePanel welcome_panel;
    public static NewProjectPanel new_project_panel;

    public static JTabbedPane dash_panel;
    public static MemberManagementPanel member_management_panel;
    public static JScrollPane member_management_scroll_panel;
    public static RequirementManagementPanel requirement_management_panel;
    public static JScrollPane requirement_management_scroll_panel;
    // TODO(Fran): Refactor all variable names to fit Java camelCase standard
    public static CategorizeManagementPanel categorizeManagementPanel;
    public static JScrollPane categorizeManagementScrollPanel;
    public static TaskManagementPanel taskManagementPanel;
    public static JScrollPane taskManagementScrollPanel;
    public static GeneralInfoPanel generalInfoPanel;

    public static Project current_project = null;

    public static void CreateProject(
            String name, String manager, String description) {
        current_project = new Project(name, manager, description);
    }

    public static void SwitchLayout(String panel) {
        CardLayout cl = (CardLayout)card_panel.getLayout();
        cl.show(ProManager.card_panel, panel);
    }

    public static void main(String[] args) {
        field_border = BorderFactory.createLineBorder(Color.BLACK);

        window = new JFrame("ProManager");
        window.setTitle("ProManager");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);

        welcome_panel = new WelcomePanel();
        new_project_panel = new NewProjectPanel();
        dash_panel = new JTabbedPane();
        dash_panel.setTabPlacement(JTabbedPane.BOTTOM);
        dash_panel.addChangeListener(
            (e) -> {
            if(dash_panel.getSelectedIndex() == 2) {
                categorizeManagementPanel.AddAllCategorizePanels();
                JScrollBar scroll =
                    categorizeManagementScrollPanel.getVerticalScrollBar();
                scroll.setValue(scroll.getMinimum());
                categorizeManagementScrollPanel.revalidate();
                categorizeManagementScrollPanel.repaint();
            }
            if(dash_panel.getSelectedIndex() == 3) {
                taskManagementPanel.Redo();
            }
        });


        card_panel = new JPanel(new CardLayout());
        card_panel.add("Welcome", welcome_panel);
        card_panel.add("New Project", new_project_panel);

        card_panel.add("Dash", dash_panel);

        window.add(card_panel);

        window.setVisible(true);
    }
}
