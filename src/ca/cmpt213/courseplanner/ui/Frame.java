package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Faranak Nobakhtian on 2016-08-01.
 * This class is the main GUI frame and it calls each panel to make itself
 */
public class Frame extends JFrame{

    private Model model;
    private int LEFT_RIGHT_PANELS_WIDTH = 250;

    public Frame(Model model){
        super("FAS Course Planner");
        this.model = model;
        setLayout(new BorderLayout());
        add(addLeftPanels(), BorderLayout.WEST);
        add(addRightPanel(), BorderLayout.EAST);
        add(addCentralPanel(), BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1200, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //This is left panel which include CourseListFilter panel and courseList panel
    public Component addLeftPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(addCourseListFilterPanel(), BorderLayout.NORTH);
        leftPanel.add(addCourseListPanel(), BorderLayout.WEST);
        return leftPanel;
    }

    public Component addRightPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(addStatisticPanel(), BorderLayout.NORTH);
        rightPanel.add(addDetailsOfCourseOfferingPanel(), BorderLayout.WEST);
        return rightPanel;
    }

    public Component addCentralPanel(){
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
        centralPanel.add(addCourseOfferingBySemester());
        return centralPanel;
    }

    private Component addCourseListFilterPanel(){
        CourseListFilter courseListFilterPanel = new CourseListFilter(model, LEFT_RIGHT_PANELS_WIDTH);
        return courseListFilterPanel;
    }

    private Component addCourseListPanel(){
        CourseList courseListPanel = new CourseList(model, LEFT_RIGHT_PANELS_WIDTH);
        return courseListPanel;
    }

    private Component addStatisticPanel(){
        Statistics statisticsPanel = new Statistics(model, LEFT_RIGHT_PANELS_WIDTH);
        return statisticsPanel;
    }

    private Component addCourseOfferingBySemester(){
        CourseOfferingBySemester courseOfferingBySemester = new CourseOfferingBySemester(model);
        return courseOfferingBySemester;
    }

    private Component addDetailsOfCourseOfferingPanel(){
        DetailsOfCourseOffering detailsOfCourseOfferingPanel = new DetailsOfCourseOffering(model, LEFT_RIGHT_PANELS_WIDTH);
        return detailsOfCourseOfferingPanel;
    }
}
