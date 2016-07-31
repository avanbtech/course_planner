package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javafx.scene.control.ComboBox;

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

    public CourseList(Model model){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        Set<String> catalogNumbers = getModel().getCatalogNumbersInSpecificDepartment();
        courseList = new JList(catalogNumbers.toArray());
        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        courseList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(courseList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        userContentsPanel.add(listScroller);
        //ComboBox courseList = new ComboBox();
        //this.add(courseList);
    }


}
