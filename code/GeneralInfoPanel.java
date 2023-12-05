import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream ;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;

public class GeneralInfoPanel extends JPanel {

    static JButton button_save;
    static int field_width = 19;

    static JLabel label_project_name;
    static JTextField field_project_name;

    static JLabel label_manager_name;
    static JTextField field_manager_name;

    static JLabel label_project_description;
    static JTextArea area_project_description;
    static int width_area_project_description = field_width;
    static int height_area_project_description = 1;

    GeneralInfoPanel() {
        button_save = new JButton("Save");
        button_save.setFont(ProManager.default_font);
        button_save.addActionListener(
            (e) -> {
                String filename = ProManager.current_project.project_name+
                    ".proma";
                File output = new File(filename);
                try {
                    FileOutputStream fw =
                        new FileOutputStream(output, false);
                    ObjectOutputStream oos = new ObjectOutputStream(fw);
                    oos.writeObject(ProManager.current_project);
                    oos.close();
                    fw.close();
                }
                catch(IOException exception) {
                }
            }
        );

        label_project_name = new JLabel("Project Name:");
        label_project_name.setFont(ProManager.default_font);

        field_project_name = new JTextField(ProManager.current_project.project_name);
        field_project_name.setFont(ProManager.default_font);
        field_project_name.setColumns(field_width);
        field_project_name.setEditable(false);

        label_manager_name = new JLabel("Project Manager:");
        label_manager_name.setFont(ProManager.default_font);

        field_manager_name = new JTextField(ProManager.current_project.project_manager);
        field_manager_name.setFont(ProManager.default_font);
        field_manager_name.setColumns(field_width);
        field_manager_name.setEditable(false);

        label_project_description = new JLabel("Project Description:");
        label_project_description.setFont(ProManager.default_font);

        area_project_description = new JTextArea(ProManager.current_project.project_description,
                height_area_project_description, width_area_project_description);
        area_project_description.setFont(ProManager.default_font);
        area_project_description.setLineWrap(true);
        area_project_description.setWrapStyleWord(true);
        area_project_description.setTabSize(2);
        field_manager_name.setEditable(false);

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
                  .addComponent(button_save,
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
                  .addComponent(button_save,
                      GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                      GroupLayout.PREFERRED_SIZE)
              )
              .addContainerGap(panel_gap, panel_gap)
        );
    }
}
