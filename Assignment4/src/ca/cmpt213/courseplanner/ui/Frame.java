package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class Frame extends JFrame{

    private Model model;
    private int LEFT_RIGHT_PANELS_WIDTH = 250;
    private int STATISTICS_PANEL_HEIGHT = 400;
//    private CourseListFilter courseListFilterPanel;
//    private CourseList courseListPanel;
//    private CourseOfferingBySemester courseOfferingBySemesterPanel;
//    private Statistics statisticsPanel;
//    private DetailsOfCourseOffering detailsOfCourseOfferingPanel;
/*
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


*/

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
        //rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        //rightPanel.add(addStatisticPanel());
        //rightPanel.add(addDetailsOfCourseOfferingPanel());
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
