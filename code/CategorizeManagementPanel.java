import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

class CategorizePanel extends JPanel {
    public JTextArea requirementText;
    public JComboBox<String> categoryList;
    public Requirement requirement;

    static Dimension panelSize = new Dimension(680, 250);
    static String dropdownList[] = new String[]
                                 {"UNASSIGNED",
                                  "USER",
                                  "SYSTEM",
                                  "BUSINESS",
                                  "REGULATORY",
                                  "INTERFACE",
                                  "DESIGN" };

    CategorizePanel(Requirement requirement) {
        this.setLayout(new BorderLayout());

        this.requirement = requirement;
        requirementText = new JTextArea(requirement.text, 2, 8);
        requirementText.setEditable(false);
        requirementText.setFont(ProManager.default_font);
        requirementText.setLineWrap(true);
        requirementText.setWrapStyleWord(true);

        categoryList = new JComboBox<String>(dropdownList);
        categoryList.setFont(ProManager.default_font);
        categoryList.setSelectedIndex(RequirementCategory.fromEnum(requirement.category));
        categoryList.addActionListener(
                (e) -> {
                    JComboBox box = (JComboBox)e.getSource();
                    int category_index = (int)box.getSelectedIndex();
                    this.requirement.category = RequirementCategory.fromIndex(category_index);
                });

        this.add(requirementText, BorderLayout.CENTER);
        this.add(categoryList, BorderLayout.PAGE_START);
        this.setMinimumSize(panelSize); this.setPreferredSize(panelSize); this.setMaximumSize(panelSize);
    }
}

public class CategorizeManagementPanel extends JPanel {
    public static LinkedList<CategorizePanel> categorizePanels;
    public static JPanel selection;
    static Dimension selectionPanelSize = new Dimension(980, 80);

    void ClearCategorizePanels() {
        categorizePanels.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.add(selection);
    }

    void AddCategorizePanel(Requirement requirement) {
        CategorizePanel panel = new CategorizePanel(requirement);
        categorizePanels.add(panel);
        this.add(panel);
    }
    void AddAllCategorizePanels() {
        ClearCategorizePanels();
        for(int i=0;
                i<ProManager.current_project.requirements.size();
                i++) {
            Requirement requirement = ProManager.current_project.requirements.get(i);
            AddCategorizePanel(requirement);
        }
    }


    CategorizeManagementPanel() {
        categorizePanels = new LinkedList<CategorizePanel>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JRadioButton nonFunctionalButton = new JRadioButton("Nonfunctional");
        nonFunctionalButton.setFont(ProManager.default_font);
        nonFunctionalButton.addActionListener(
            (e) -> {
                ClearCategorizePanels();
                for(int i=0;
                        i<ProManager.current_project.requirements.size();
                        i++) {
                    Requirement requirement = ProManager.current_project.requirements
                        .get(i);
                    if(!requirement.isFunctional) {
                        AddCategorizePanel(requirement);
                    }
                }
            }
        );

        JRadioButton functionalButton = new JRadioButton("Functional");
        functionalButton.setFont(ProManager.default_font);
        functionalButton.addActionListener(
            (e) -> {
                ClearCategorizePanels();
                for(int i=0;
                        i<ProManager.current_project.requirements.size();
                        i++) {
                    Requirement requirement = ProManager.current_project.requirements
                        .get(i);
                    if(requirement.isFunctional) {
                        AddCategorizePanel(requirement);
                    }
                }
            }
        );

        JRadioButton allButton = new JRadioButton("All");
        allButton.setFont(ProManager.default_font);
        allButton.addActionListener(
            (e) -> {
                ClearCategorizePanels();
                for(int i=0;
                        i<ProManager.current_project.requirements.size();
                        i++) {
                    Requirement requirement = ProManager.current_project.requirements
                        .get(i);
                    AddCategorizePanel(requirement);
                }
            }
        );

        JPanel button_view = new JPanel();
        ButtonGroup group = new ButtonGroup();
        group.add(nonFunctionalButton);
        group.add(functionalButton);
        group.add(allButton);
        JLabel sortByLabel = new JLabel("Filter by: ");
        sortByLabel.setFont(ProManager.default_font);
        button_view.add(sortByLabel);
        button_view.add(allButton);
        button_view.add(nonFunctionalButton);
        button_view.add(functionalButton);
        allButton.setSelected(true);

        selection = new JPanel(new BorderLayout());
        selection.add(button_view, BorderLayout.SOUTH);

        selection.setMinimumSize(selectionPanelSize); selection.setPreferredSize(selectionPanelSize); selection.setMaximumSize(selectionPanelSize);
        this.add(selection);

        for(int i=0;
                i<ProManager.current_project.requirements.size();
                i++) {
            Requirement requirement = ProManager.current_project.requirements
                .get(i);
            AddCategorizePanel(requirement);
        }
    }
}
