import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;

public class NewProjectPanel extends JPanel {

    static JButton button_create;
    static JButton button_back;
    static int field_width = 19;

    static JLabel label_project_name;
    static JTextField field_project_name;

    static JLabel label_manager_name;
    static JTextField field_manager_name;

    static JLabel label_project_description;
    static JTextArea area_project_description;
    static int width_area_project_description = field_width;
    static int height_area_project_description = 1;

    NewProjectPanel() {
        button_back = new JButton("Back");
        button_back.setFont(ProManager.default_font);
        button_back.addActionListener(
            (e) -> {
                ProManager.SwitchLayout("Welcome");
            }
        );
        button_create = new JButton("Create");
        button_create.setFont(ProManager.default_font);
        button_create.addActionListener(
            (e) -> {
                ProManager.CreateProject(
                    field_project_name.getText(),
                    field_manager_name.getText(),
                    area_project_description.getText()
                );

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

                ProManager.SwitchLayout("Dash");
            }
        );

        label_project_name = new JLabel("Project Name:");
        label_project_name.setFont(ProManager.default_font);

        field_project_name = new JTextField("");
        field_project_name.setFont(ProManager.default_font);
        field_project_name.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        field_project_name.setColumns(field_width);

        label_manager_name = new JLabel("Project Manager:");
        label_manager_name.setFont(ProManager.default_font);

        field_manager_name = new JTextField("");
        field_manager_name.setFont(ProManager.default_font);
        field_manager_name.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        field_manager_name.setColumns(field_width);

        label_project_description = new JLabel("Project Description:");
        label_project_description.setFont(ProManager.default_font);

        area_project_description = new JTextArea("",
                height_area_project_description, width_area_project_description);
        area_project_description.setFont(ProManager.default_font);
        area_project_description.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        area_project_description.setLineWrap(true);
        area_project_description.setWrapStyleWord(true);
        area_project_description.setTabSize(2);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        this.setLayout(layout);

        this.setBackground(Color.WHITE);
        
        int panel_gap = 100;
        int vertical_field_gap = 20;
        int label_field_gap = 40;

        layout.setHorizontalGroup(
           layout.createSequentialGroup()
              .addContainerGap(panel_gap, panel_gap)
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(button_back,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  .addComponent(label_project_name)
                  .addComponent(label_manager_name)
                  .addComponent(label_project_description)
                  )
              .addContainerGap(label_field_gap, label_field_gap)

              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                  .addComponent(field_project_name,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  .addComponent(field_manager_name,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  .addComponent(area_project_description,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  .addComponent(button_create,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  )
        );
        layout.setVerticalGroup(
           layout.createSequentialGroup()
              .addContainerGap(panel_gap, panel_gap)
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(label_project_name)
                   .addComponent(field_project_name))

              .addContainerGap(vertical_field_gap, vertical_field_gap)

              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(label_manager_name)
                   .addComponent(field_manager_name))

              .addContainerGap(vertical_field_gap, vertical_field_gap)

              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(label_project_description)
                   .addComponent(area_project_description))

              .addContainerGap(vertical_field_gap+20, vertical_field_gap+20)

              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                  .addComponent(button_create,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
                  .addComponent(button_back,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
              )
              .addContainerGap(panel_gap, panel_gap)
        );
    }
}
