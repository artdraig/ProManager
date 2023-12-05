import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

class RequirementPanel extends JPanel {
    public JLabel requirement_label;
    public JTextArea requirement_area;
    public JButton button_save;
    public JButton button_remove;
    public JRadioButton nonfunctional_button;
    public JRadioButton functional_button;

    public static Dimension button_size = new Dimension(
            60, ProManager.default_font.getSize()/2
        );

    static Dimension panel_size = new Dimension(680, 250);

    RequirementPanel(String string) {
        this.setLayout(new BorderLayout());

        button_remove = new JButton("X");
        button_remove.setFont(ProManager.default_font);
        button_remove.setPreferredSize(button_size);
        button_remove.addActionListener(
            (e) -> {
                    if(ProManager.current_project.requirements.size() <= 1) {
                    }
                    else {
                        for(int i=0;
                            i < ProManager.requirement_management_panel.
                            requirement_panels.size();
                            i++) {
                            JButton button = (JButton)e.getSource();
                            JPanel panel = (JPanel)(button.getParent());
                            if(panel == ProManager.requirement_management_panel.requirement_panels.get(i)) {
                                ProManager.requirement_management_panel.RemoveRequirementPanel(panel, i);
                                ProManager.current_project.RemoveRequirement(i);
                                this.revalidate();
                                this.repaint();
                            break;
                        }
                    }
                }
            }
        );
        this.add(button_remove, BorderLayout.WEST);

        requirement_label = new JLabel("Requirement:");
        requirement_label.setFont(ProManager.default_font);
        requirement_area = new JTextArea("", 2, 8);
        requirement_area.setFont(ProManager.default_font);
        requirement_area.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        requirement_area.setLineWrap(true);
        requirement_area.setWrapStyleWord(true);

        nonfunctional_button = new JRadioButton("Nonfunctional");
        nonfunctional_button.setFont(ProManager.default_font);
        functional_button = new JRadioButton("Functional");
        functional_button.setFont(ProManager.default_font);
        JPanel button_view = new JPanel();
        ButtonGroup group = new ButtonGroup();
        group.add(nonfunctional_button);
        group.add(functional_button);
        button_view.add(nonfunctional_button);
        button_view.add(functional_button);

        this.add(requirement_label, BorderLayout.NORTH);
        this.add(requirement_area, BorderLayout.CENTER);
        this.add(button_view, BorderLayout.SOUTH);
        button_save = new JButton("Save");
        button_save.setFont(ProManager.default_font);
        button_save.addActionListener(
            (e) -> {
                    for(int i=0;
                        i < ProManager.requirement_management_panel.
                        requirement_panels.size();
                        i++) {
                        JButton button = (JButton)e.getSource();
                        JPanel panel = (JPanel)(button.getParent());
                        if(panel == ProManager.requirement_management_panel.requirement_panels.get(i)) {
                            JTextArea area = (JTextArea)panel.getComponent(2);
                            Requirement requirement = new Requirement();
                            requirement.text = area.getText();
                            JPanel button_panel = (JPanel)panel.getComponent(3);
                            JRadioButton funct_button = (JRadioButton)button_panel.getComponent(1);
                            if(funct_button.isSelected()) {
                                requirement.isFunctional = true;
                            }
                            else {
                                requirement.isFunctional = false;
                            }
                            Requirement old_req = ProManager.current_project.requirements.get(i);
                            requirement.category = old_req.category;

                            ProManager.current_project.requirements.set(i, requirement);
                        break;
                    }
                }
            }
        );
        this.add(button_save, BorderLayout.EAST);

        this.setMinimumSize(panel_size); this.setPreferredSize(panel_size); this.setMaximumSize(panel_size);
    }
}

public class RequirementManagementPanel extends JPanel {
    public static JButton button_add;
    public static LinkedList<RequirementPanel> requirement_panels;

    void AddRequirementPanel() {
        this.remove(button_add);
        Requirement requirement = ProManager.current_project.requirements
            .get(requirement_panels.size());
        String text = requirement.text;
        RequirementPanel requirement_panel = new RequirementPanel(text);
        requirement_panel.requirement_area.setText(text);
        if(requirement.isFunctional) {
            requirement_panel.functional_button.setSelected(true);
        }
        else {
            requirement_panel.nonfunctional_button.setSelected(true);
        }
        requirement_panels.add(requirement_panel);

        this.add(requirement_panel);

        this.add(button_add);
        this.revalidate();
        this.repaint();
    }

    void RemoveRequirementPanel(JPanel panel, int i) {
        this.remove(panel);
        requirement_panels.remove(i);
        this.revalidate();
        this.repaint();
    }

    RequirementManagementPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        requirement_panels = new LinkedList<RequirementPanel>();

        button_add = new JButton("Add");
        button_add.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_add.setFont(ProManager.default_font);
        button_add.addActionListener(
            (e) -> {
                ProManager.current_project.AddRequirement();
                AddRequirementPanel();
            }
        );

        for(int i=0;
                i < ProManager.current_project.requirements.size();
                i++) {
            AddRequirementPanel();
        }

        this.add(button_add);
    }
}
