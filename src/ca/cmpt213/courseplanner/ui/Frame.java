package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class Frame extends JFrame{

    private Model model;
//    private CourseListFilter courseListFilterPanel;
//    private CourseList courseListPanel;
//    private CourseOfferingBySemester courseOfferingBySemesterPanel;
//    private Statistics statisticsPanel;
//    private DetailsOfCourseOffering detailsOfCourseOfferingPanel;

    public Frame(Model model){
//        courseListPanel = new CourseList(model);
        super("FAS Course Planner");
        this.model = model;
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        designFrame();
        pack();
        setVisible(true);
    }

    public void designFrame(){
        addCourseListFilterPanel();
        addCourseListPanel();
    }

    public void addCourseListFilterPanel(){
        CourseListFilter courseListFilterPanel = new CourseListFilter(model);
        GridBagConstraints c = makeConstraints(0, 0);
        add(courseListFilterPanel, c);
    }

    public void addCourseListPanel(){
        CourseList courseListPanel = new CourseList(model);
        GridBagConstraints c = makeConstraints(0, 1);
        add(courseListPanel, c);
    }

    public GridBagConstraints makeConstraints(int i, int j){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = i;
        c.gridy = j;
        return c;
    }
}
