package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class CourseOfferingBySemester extends MyABCPanel{
    static String title = "Course Offering by Semester";
    JPanel userContentsPanel;

    public CourseOfferingBySemester(Model model){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setOpaque(true);
        userContentsPanel.setBackground(Color.white);
    }
}
