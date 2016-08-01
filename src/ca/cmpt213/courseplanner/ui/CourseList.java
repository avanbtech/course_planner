package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javafx.scene.control.ComboBox;
import java.util.Arrays;

import javax.swing.*;
import java.util.Set;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class CourseList extends MyABCPanel {
    static String title = "Course List";
    JPanel userContentsPanel;
    JList courseList;
    DefaultListModel listModel;

    public CourseList(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setLayout(new BoxLayout(userContentsPanel, BoxLayout.PAGE_AXIS));
        Set<String> catalogNumbers = getModel().getCatalogNumbersInSpecificDepartment();
        listModel = new DefaultListModel();
        courseList = new JList(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        courseList.setVisibleRowCount(-1);
        courseList.setPreferredSize(new Dimension(width, 50));
        courseList.setFixedCellWidth(width / 2 - 2);
        JScrollPane listScroller = new JScrollPane(courseList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        userContentsPanel.add(listScroller);
        model.registerCourseListListener(makeCourseListener());
        //ComboBox courseList = new ComboBox();
        //this.add(courseList);
    }

    private CourseListListener makeCourseListener(){
        return new CourseListListener() {
            @Override
            public void updateCourseList() {
                Set<String> catalogNumbers = getModel().getCatalogNumbersInSpecificDepartment();
                String[] courseNumbers = catalogNumbers.toArray(new String[catalogNumbers.size()]);
                Arrays.sort(courseNumbers);
                String departmentName = getModel().getSubject();
                listModel.clear();
                int index = 0;
                for(String catalogNum : courseNumbers){
                    listModel.add(index, departmentName + " " + catalogNum);
                    index++;
                }
            }
        };
    }


}
