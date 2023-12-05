import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

class TaskPanel extends JPanel {

    public JLabel detailLabel;
    public JTextArea detailArea;
    public JScrollPane detailScrollArea;
    public JButton buttonSave;

    public JPanel topPanel;
    public JButton buttonRemove;
    public JLabel memberLabel;
    public JComboBox<String> taskMember;

    public JPanel bottomPanel;
    public JLabel dateLabel;
    public JComboBox<Integer> dayBox;
    public JComboBox<Integer> monthBox;
    public JComboBox<Integer> yearBox;
    static Dimension dropDownSize = new Dimension(400, 60);
    static Dimension panelSize = new Dimension(1000, 150);
    static Dimension smallPanelSize = new Dimension(1000, 60);
    static Dimension bigPanelSize = new Dimension(1000, 210);

    static Integer day[] = new Integer[]{
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
        17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    static Integer month[] = new Integer[]{
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    static Integer year[] = new Integer[] {
        2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030,
        2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038,
        2039, 2040, 2041, 2042, 2043, 2044, 2045, 2046,
        2047, 2048, 2049, 2050, 2051, 2052, 2053, 2054,
        2055, 2056, 2057, 2058, 2059, 2060};


    TaskPanel(Task task) {
        JPanel completePanel = new JPanel();
        completePanel.setLayout(new BoxLayout(completePanel, BoxLayout.Y_AXIS));

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        bottomPanel = new JPanel();

        detailLabel = new JLabel("Task detail: ");
        detailLabel.setFont(ProManager.default_font);

        detailArea = new JTextArea(task.description, 2, 14);
        detailArea.setFont(ProManager.default_font);
        detailArea.setBorder(BorderFactory.createCompoundBorder(
                    ProManager.field_border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);

        detailScrollArea = new JScrollPane(detailArea);
        detailScrollArea.getVerticalScrollBar().setUnitIncrement(16);
        detailScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        buttonSave = new JButton("Save");
        buttonSave.setFont(ProManager.default_font);
        buttonSave.addActionListener(
            (e) -> {
                for(int i=0;
                    i < ProManager.taskManagementPanel.taskPanels.size();
                    i++) {
                    JButton button = (JButton)e.getSource();
                    JPanel buttonPanel = (JPanel)(button.getParent());
                    if(buttonPanel == ProManager.taskManagementPanel.taskPanels.get(i).topPanel) {
                        TaskPanel panel = ProManager.taskManagementPanel.taskPanels.get(i);
                        Task selTask= ProManager.current_project.tasks.get(i);
                        selTask.description = panel.detailArea.getText();
                        selTask.doneBy = (String)panel.taskMember.getSelectedItem();
                        selTask.year = (int) panel.yearBox.getSelectedItem();
                        selTask.month = (int) panel.monthBox.getSelectedItem();
                        selTask.day = (int)panel.dayBox.getSelectedItem();
                    }
                }
            });

        buttonRemove = new JButton("X");
        buttonRemove.setFont(ProManager.default_font);
        buttonRemove.addActionListener(
            (e) -> {
                for(int i=0;
                    i < ProManager.taskManagementPanel.taskPanels.size();
                    i++) {
                    JButton button = (JButton)e.getSource();
                    JPanel buttonPanel = (JPanel)(button.getParent());
                    if(buttonPanel == ProManager.taskManagementPanel.taskPanels.get(i).bottomPanel) {
                        JPanel panel = ProManager.taskManagementPanel.taskPanels.get(i);
                        ProManager.taskManagementPanel.RemoveTaskPanel(i);
                    }
                }
            });
        memberLabel = new JLabel("Done by: ");
        memberLabel.setFont(ProManager.default_font);

        taskMember = new JComboBox<String>();
        for(int i=0;i<ProManager.current_project.members.size(); i++)
        {
            String member = ProManager.current_project.members.get(i);
            taskMember.addItem(member);
            if(member == task.doneBy) {
                taskMember.setSelectedItem(member);
            }
        }
        taskMember.setFont(ProManager.default_font);
        taskMember.setMaximumSize(dropDownSize); taskMember.setMinimumSize(dropDownSize); taskMember.setPreferredSize(dropDownSize);

        dateLabel = new JLabel(" on: ");
        dateLabel .setFont(ProManager.default_font);

        yearBox = new JComboBox<Integer>(year);
        yearBox.setFont(ProManager.default_font);
        monthBox = new JComboBox<Integer>(month);
        monthBox.setFont(ProManager.default_font);
        dayBox = new JComboBox<Integer>(day);
        dayBox.setFont(ProManager.default_font);

        yearBox.setSelectedIndex(task.year-2023);
        monthBox.setSelectedIndex(task.month-1);
        dayBox.setSelectedIndex(task.day-1);

        topPanel.add(detailLabel, BorderLayout.WEST);
        topPanel.add(detailScrollArea, BorderLayout.CENTER);
        topPanel.add(buttonSave, BorderLayout.EAST);
        topPanel.setMinimumSize(panelSize); topPanel.setPreferredSize(panelSize); topPanel.setMaximumSize(panelSize);
        bottomPanel.add(buttonRemove);
        bottomPanel.add(memberLabel);
        bottomPanel.add(taskMember);
        bottomPanel.add(dateLabel);
        bottomPanel.add(monthBox);bottomPanel.add(dayBox);bottomPanel.add(yearBox);
        bottomPanel.setMinimumSize(smallPanelSize); bottomPanel.setPreferredSize(smallPanelSize); bottomPanel.setMaximumSize(smallPanelSize);

        completePanel.add(topPanel);
        completePanel.add(bottomPanel);
        completePanel.setMinimumSize(bigPanelSize); completePanel.setPreferredSize(bigPanelSize); completePanel.setMaximumSize(bigPanelSize);
        this.add(completePanel);
    }
}

public class TaskManagementPanel extends JPanel {
    public static LinkedList<TaskPanel> taskPanels;
    public static JButton addButton;
    public JButton buttonGenerate;
    
    void AddTaskPanel(Task task) { 
        this.remove(addButton);
        TaskPanel taskPanel = new TaskPanel(task);
        taskPanels.add(taskPanel);

        this.add(taskPanel);
        this.add(addButton);
        this.revalidate();
        this.repaint();
    }
    void RemoveTaskPanel(int i) {
        TaskPanel panel = taskPanels.get(i);
        ProManager.current_project.RemoveTask(i);
        taskPanels.remove(panel);
        this.remove(panel);
        this.revalidate();
        this.repaint();
    }

    void Redo() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.removeAll();
        buttonGenerate = new JButton("Generate summary?");
        buttonGenerate.setFont(ProManager.default_font);
        buttonGenerate.addActionListener(
                (e) -> {
                    ProManager.card_panel.setVisible(false);

                    JPanel dialog = new JPanel();
                    dialog.setLayout(new BorderLayout());

                    JPanel rowPanel = new JPanel();
                    JComboBox yearBox = new JComboBox<Integer>(TaskPanel.year);
                    yearBox.setFont(ProManager.default_font);
                    JComboBox monthBox = new JComboBox<Integer>(TaskPanel.month);
                    monthBox.setFont(ProManager.default_font);
                    JComboBox dayBox = new JComboBox<Integer>(TaskPanel.day);
                    dayBox.setFont(ProManager.default_font);

                    rowPanel.add(yearBox);
                    rowPanel.add(monthBox);
                    rowPanel.add(dayBox);

                    JRadioButton daily = new JRadioButton("Daily");
                    daily.setFont(ProManager.default_font);

                    JRadioButton monthly = new JRadioButton("Monthly");
                    monthly.setFont(ProManager.default_font);

                    JPanel button_view = new JPanel();
                    ButtonGroup group = new ButtonGroup();
                    group.add(daily);
                    group.add(monthly);

                    JLabel prompt = new JLabel("Select interval:");
                    button_view.add(prompt);
                    button_view.add(daily);
                    button_view.add(monthly);
                    prompt.setFont(ProManager.default_font);
                    dialog.add(button_view, BorderLayout.NORTH);

                    JButton generateLogButton = new JButton("Generate Log");
                    generateLogButton.setSize(new Dimension(240, 80));
                    generateLogButton.setMaximumSize(new Dimension(240, 80));
                    generateLogButton.setMinimumSize(new Dimension(240, 80));
                    generateLogButton.setFont(ProManager.default_font);
                    generateLogButton.addActionListener(
                            (tes) -> {
                                int year = (int) yearBox.getSelectedItem();
                                int month = (int) monthBox.getSelectedItem();
                                int day = (int)dayBox.getSelectedItem();
                                LinkedList<Task> correctEntries = new LinkedList<Task>();
                                for(int i=0;
                                        i<ProManager.current_project.tasks.size();
                                        i++){
                                    Task task = ProManager.current_project.tasks.get(i);
                                    if(task.year == year) { 
                                        if(task.month == month) {
                                            if(daily.isSelected()) {
                                                if(day == task.day) {
                                                    correctEntries.add(task);
                                                }
                                            }
                                            else {
                                                correctEntries.add(task);
                                            }
                                        }
                                    }
                                }
                                if(correctEntries.size() > 0) {
                                    String filename = ProManager.current_project.project_name+
                                        "_log_"+year+"_"+month+"_"+day+".txt";
                                    File output = new File(filename);
                                    try {
                                        FileWriter fw =
                                            new FileWriter(output, false);
                                        String title = "Log "+ProManager.current_project.project_name
                                        +year+"/"+month+"/"+day+"\n";
                                        fw.write(title);
                                        for(int i=0;
                                                i<correctEntries.size();
                                                i++)
                                        {
                                            fw.write(correctEntries.get(i)+"\n");
                                        }
                                        fw.close();
                                    }
                                    catch(IOException exception) {
                                    }
                                }
                            });
                    dialog.add(generateLogButton, BorderLayout.EAST);

                    JButton returnButton = new JButton("Cancel");
                    returnButton.setSize(new Dimension(240, 80));
                    returnButton.setMaximumSize(new Dimension(240, 80));
                    returnButton.setMinimumSize(new Dimension(240, 80));
                    returnButton.setFont(ProManager.default_font);
                    returnButton.addActionListener(
                            (te) -> {
                                dialog.setVisible(false);
                                ProManager.card_panel.setVisible(true);
                            });
                    dialog.add(returnButton, BorderLayout.WEST);

                    dialog.add(rowPanel, BorderLayout.CENTER);
                    daily.setSelected(true);

                    ProManager.card_panel.setVisible(false);
                    ProManager.window.add(dialog);
                });
        this.add(buttonGenerate, BorderLayout.CENTER);
        addButton = new JButton("Add");
        addButton.setFont(ProManager.default_font);
        addButton.addActionListener(
            (e) -> {
                ProManager.current_project.AddTask();
                Task task = ProManager.current_project.tasks.get(taskPanels.size());
                AddTaskPanel(task);
            });
        taskPanels = new LinkedList<TaskPanel>();
        for(int i=0;
                i<ProManager.current_project.tasks.size();
                i++)
        {
            Task task = ProManager.current_project.tasks.get(i);
            AddTaskPanel(task);
        }
    }

    TaskManagementPanel() {
        Redo();
    }
}
