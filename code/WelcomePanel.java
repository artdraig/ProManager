import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.lang.ClassNotFoundException;
import java.io.FileInputStream ;
import java.io.EOFException;
import java.io.ObjectInputStream ;
import java.io.IOException;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
            final JFileChooser fc = new JFileChooser();
            //In response to a button click:
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
                File file = fc.getSelectedFile();
                try
                {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    System.out.println(file.getName());
                    ProManager.current_project = (Project) ois.readObject();
                    ois.close();
                    fis.close();
                }
                catch(ClassNotFoundException exceieieei) {
                    System.out.println("nope?");
                }
                catch(IOException io) {
                    System.out.println(io);
                }
                    System.out.println(ProManager.current_project.toString());
                    System.out.println("Success?");


                ProManager.member_management_panel = new MemberManagementPanel();
                ProManager.member_management_scroll_panel =
                    new JScrollPane(ProManager.member_management_panel);
                ProManager.member_management_scroll_panel.
                    getVerticalScrollBar().setUnitIncrement(16);
                ProManager.member_management_scroll_panel.
                    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                ProManager.dash_panel.addTab("Member Management",
                        ProManager.member_management_scroll_panel);

                JLabel member_management_tab = new JLabel("Members");
                member_management_tab.setFont(ProManager.default_font);
                ProManager.dash_panel.setTabComponentAt(0, member_management_tab);

                ProManager.requirement_management_panel = new RequirementManagementPanel();
                ProManager.requirement_management_scroll_panel =
                    new JScrollPane(ProManager.requirement_management_panel);
                ProManager.requirement_management_scroll_panel.
                    getVerticalScrollBar().setUnitIncrement(16);
                ProManager.requirement_management_scroll_panel.
                    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                ProManager.dash_panel.addTab("Requirement Management",
                        ProManager.requirement_management_scroll_panel);

                JLabel requirement_management_tab = new JLabel("Requirements");
                requirement_management_tab.setFont(ProManager.default_font);
                ProManager.dash_panel.setTabComponentAt(1, requirement_management_tab);

                ProManager.categorizeManagementPanel = new CategorizeManagementPanel();
                ProManager.categorizeManagementScrollPanel =
                    new JScrollPane(ProManager.categorizeManagementPanel);
                ProManager.categorizeManagementScrollPanel.
                    getVerticalScrollBar().setUnitIncrement(16);
                ProManager.categorizeManagementScrollPanel.
                    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                ProManager.dash_panel.addTab("Categorize Management",
                        ProManager.categorizeManagementScrollPanel);

                JLabel categorizeManagementTab = new JLabel("Categories");
                categorizeManagementTab.setFont(ProManager.default_font);
                ProManager.dash_panel.setTabComponentAt(2, categorizeManagementTab);

                ProManager.taskManagementPanel = new TaskManagementPanel();
                ProManager.taskManagementScrollPanel =
                    new JScrollPane(ProManager.taskManagementPanel);
                ProManager.taskManagementScrollPanel.
                    getVerticalScrollBar().setUnitIncrement(16);
                ProManager.taskManagementScrollPanel.
                    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                ProManager.dash_panel.addTab("Task Management",
                        ProManager.taskManagementScrollPanel);

                JLabel taskManagementTab = new JLabel("Tasks");
                taskManagementTab.setFont(ProManager.default_font);
                ProManager.dash_panel.setTabComponentAt(3, taskManagementTab);

                ProManager.generalInfoPanel = new GeneralInfoPanel();
                ProManager.dash_panel.addTab("General Info",
                        ProManager.generalInfoPanel);

                JLabel generalManagementTab = new JLabel("General");
                generalManagementTab.setFont(ProManager.default_font);
                ProManager.dash_panel.setTabComponentAt(4, generalManagementTab);

                ProManager.SwitchLayout("Dash");
                }});
            
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
