package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javafx.scene.control.ComboBox;

import javax.swing.*;

/**
 * Created by faranakpouya on 2016-07-25.
 */
public class CourseListFilter extends MyABCPanel {
    static String title = "Course List Filter";

    public CourseListFilter(Model model){
        super(model, title);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.maketopPanel();
        JCheckBox undergrad = new JCheckBox("Include undergrad courses");
        this.add(undergrad);
        JCheckBox grad = new JCheckBox("Include grad courses");
        this.add(grad);
    }

    public void maketopPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        JLabel courseListFilterLabel = new JLabel("Department");
        topPanel.add(courseListFilterLabel);
        JComboBox department = new JComboBox();
        department.addItem(model.getDepartments());
        this.add(department);
    }
}
