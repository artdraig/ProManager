import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

class MemberPanel extends JPanel {
    static int field_width = 10;
    static Dimension panel_size = new Dimension(940, 80);

    public JButton button_remove;
    public JLabel member_name;
    public JTextField field_member_name;
    public JButton button_save;

    MemberPanel(String string) {
        button_remove = new JButton("X");
        button_remove.setFont(ProManager.default_font);
        button_remove.addActionListener(
            (e) -> {
                for(int i=0;
                        i < ProManager.member_management_panel.member_panels.size();
                        i++) {
                    JButton button = (JButton)e.getSource();
                    JPanel panel = (JPanel)button.getParent();
                    if( ProManager.member_management_panel.
                            member_panels.get(i) == panel) {
                        ProManager.member_management_panel.RemoveMemberPanel(panel, i);
                        ProManager.current_project.RemoveMember(i);
                        this.revalidate();
                        this.repaint();
                        break;
                    }
                }
            }
        );

        member_name = new JLabel("  Member Name:  ");
        member_name.setFont(ProManager.default_font);
        field_member_name = new JTextField(string);
        field_member_name.setFont(ProManager.default_font);
        field_member_name.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        field_member_name.setColumns(field_width);

        button_save = new JButton("Save");
        button_save.setFont(ProManager.default_font);
        button_save.addActionListener(
            (e) -> {
                for(int i=0;
                        i < ProManager.member_management_panel.member_panels.size();
                        i++) {
                        JButton button = (JButton)e.getSource();
                        JPanel panel = (JPanel)button.getParent();

                        if( ProManager.member_management_panel.
                            member_panels.get(i) == panel) {
                            JTextField field = (JTextField)panel.getComponent(2);
                            ProManager.current_project.members.set(i, field.getText());
                            System.out.println(ProManager.current_project.members.get(i));
                            break;
                    }
                }
            }
        );

        this.add(button_remove);
        this.add(member_name);
        this.add(field_member_name);
        this.add(button_save);
        this.setMinimumSize(panel_size); this.setPreferredSize(panel_size); this.setMaximumSize(panel_size);
    }
}

public class MemberManagementPanel extends JPanel {
    public static JButton button_add;
    public static LinkedList<MemberPanel> member_panels;

    void AddMemberPanel() {
        this.remove(button_add);
        String name = ProManager.current_project.members.get(member_panels.size());
        MemberPanel member_panel = new MemberPanel(name);
        member_panels.add(member_panel);

        this.add(member_panel);

        this.add(button_add);
        this.revalidate();
        this.repaint();
    }

    void RemoveMemberPanel(JPanel panel, int i) {
        this.remove(panel);
        member_panels.remove(i);
        this.revalidate();
        this.repaint();
    }

    MemberManagementPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        member_panels = new LinkedList<MemberPanel>();

        button_add = new JButton("Add");
        button_add.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_add.setFont(ProManager.default_font);
        button_add.addActionListener(
            (e) -> {
                ProManager.current_project.AddMember();
                AddMemberPanel();
            }
        );

        for(int i=0;
                i < ProManager.current_project.members.size();
                i++) {
            AddMemberPanel();
        }

        this.add(button_add);
    }
}
