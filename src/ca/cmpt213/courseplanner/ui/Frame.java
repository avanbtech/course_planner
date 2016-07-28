package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class Frame {

    private Model model;
    private JFrame frame;
    private CourseListFilter courseListFilterPanel;
    private CourseList courseListPanel;
    private CourseOfferingBySemester courseOfferingBySemester;
    private Statistics statistics;
    private DetailsOfCourseOffering detailsOfCourseOffering;

    public Frame(Model model){
        courseListFilterPanel = new CourseListFilter(model);
        frame = new JFrame("FAS Course Planner");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.model = model;
    }

    public void designFrame(){

    }
}
