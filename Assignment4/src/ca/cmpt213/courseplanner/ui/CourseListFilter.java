package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Set;

/**
 * Created by faranakpouya on 2016-07-25.
 */
public class CourseListFilter extends MyABCPanel {
    static String title = "Course List Filter";
    JPanel userContentsPanel;
    JComboBox departmentList;
    JCheckBox undergrad;
    JCheckBox grad;

    public CourseListFilter(Model model) {
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setLayout(new BoxLayout(userContentsPanel, BoxLayout.PAGE_AXIS));
        addDepartment();
        addUnderGradCheckBoxs();
        addGradCheckBox();
        addUpdateButton();
    }

    public void addDepartment(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(new Label("Department"));
        departmentList = new JComboBox();
        Set<String> departments = getModel().getDepartments();
        for(String aDepartment : departments){
            departmentList.addItem(aDepartment);
        }
        topPanel.add(departmentList);
        userContentsPanel.add(topPanel);
    }

    public void addUnderGradCheckBoxs(){
        undergrad = new JCheckBox("Include undergrad courses");
        undergrad.setAlignmentX(JCheckBox.LEFT);
        userContentsPanel.add(undergrad);
    }

    public void addGradCheckBox(){
        grad = new JCheckBox("Include grad courses");
        grad.setAlignmentX(JCheckBox.LEFT);
        userContentsPanel.add(grad);
    }

    public void addUpdateButton(){
        userContentsPanel.add(makeUpdateButton());
    }

    public JButton makeUpdateButton(){
        JButton update = new JButton("Update Course List");
        update.addActionListener(e -> {
            updateCourses();
        });
        return update;
    }

    private void updateCourses(){
        getModel().setCourseListFilterData(getDepartmentName(), isGradSelected(), isUndergradSelected());
    }


    private boolean isUndergradSelected(){
        return undergrad.isSelected();
    }

    private boolean isGradSelected(){
        return grad.isSelected();
    }

    private String getDepartmentName(){
        String subjectName = (String)departmentList.getSelectedItem();
        return subjectName;
    }

}