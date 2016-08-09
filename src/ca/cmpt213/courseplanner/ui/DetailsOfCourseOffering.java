package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Faranak Nobakhtian on 2016-08-01.
 * This class displays details of a selected course offering
 */
public class DetailsOfCourseOffering extends MyABCPanel{
    static String title = "Details of Course Offering";
    JPanel userContentsPanel;
    JPanel courseTypePanel;
    JTextArea courseNameLabel;
    JTextArea semesterLabel;
    JTextArea locationLabel;
    JTextArea instructorsField;

    public DetailsOfCourseOffering(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setLayout(new BoxLayout(userContentsPanel, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        userContentsPanel.add(makeTopSection(width));
        model.registerCourseDetailsListener(makeCourseDetailsListener());
    }

    private JPanel makeCourseCodePanel(){
        courseTypePanel = new JPanel();
        return courseTypePanel;
    }

    private JPanel makeTopSection(int width){
        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        JPanel internalPanel = new JPanel();
        internalPanel.setLayout(new GridLayout(3, 2, 0, 0));
        internalPanel.add(new JLabel("Course Name:"));
        internalPanel.add(makeCourseNameLabel());
        internalPanel.add(new JLabel("Semester:"));
        internalPanel.add(makeSemesterLabel());
        internalPanel.add(new JLabel("Location:"));
        internalPanel.add(makeLocationLabel());
        internalPanel.setPreferredSize(new Dimension(width, internalPanel.getPreferredSize().height));
        panel1.add(internalPanel);

        internalPanel = new JPanel();
        internalPanel.setLayout(new GridLayout(1, 2, 0, 0));
        internalPanel.add(new JLabel("Instructors:"));
        internalPanel.add(makeInstructorsField());
        panel1.add(internalPanel);

        panel1.add(makeCourseCodePanel());

        topSectionPanel.add(panel1, BorderLayout.NORTH);
        return topSectionPanel;
    }

    private JPanel makeCourseNameLabel(){
        JPanel panel = makeWhitePanel();
        courseNameLabel = new JTextArea("", 1, 1);
        panel.add(courseNameLabel);
        return panel;
    }

    private JPanel makeSemesterLabel(){
        JPanel panel = makeWhitePanel();
        semesterLabel = new JTextArea("", 1, 1);
        panel.add(semesterLabel);
        return panel;
    }

    private JPanel makeLocationLabel(){
        JPanel panel = makeWhitePanel();
        locationLabel = new JTextArea("", 1, 1);
        panel.add(locationLabel);
        return panel;
    }

    private JPanel makeInstructorsField(){
        JPanel panel = makeWhitePanel();
        instructorsField = new JTextArea("", 1, 1);
        instructorsField.setLineWrap(true);
        instructorsField.setWrapStyleWord(true);
        panel.add(instructorsField);
        return panel;
    }

    private JPanel makeWhitePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setOpaque(true);
        return panel;
    }

    private void addCourseTypeData(Course course){
        ArrayList<ComponentCode> componentCodes = course.getComponentCodeCollection().getComponentsData();
        if(componentCodes.size() == 0){
            return;
        }
        courseTypePanel.setLayout(new GridLayout(componentCodes.size(), 2, 0, 0));
        for(ComponentCode aComponentCode : componentCodes){
            courseTypePanel.add(new JLabel(aComponentCode.toString()));
            courseTypePanel.add(new JLabel("" + aComponentCode.getEnrolmentTotal() + "/" + aComponentCode.getEnrolmentCapacity()));
        }
    }

    public CourseDetailListener makeCourseDetailsListener(){
        return new CourseDetailListener() {
            @Override
            public void updateDetailsOfCourse() {
                Course course = getModel().getCourse();
                courseTypePanel.removeAll();
                if(course != null){
                    String courseName = course.getSubject() + " " + course.getCatalogNumber();
                    courseNameLabel.setText(courseName);
                    semesterLabel.setText(course.getSemester().getOriginalCode());
                    locationLabel.setText(course.getCampusLocation().toString());
                    instructorsField.setText(course.getInstructorText());
                    addCourseTypeData(course);
                }
                else{
                    courseNameLabel.setText("");
                    semesterLabel.setText("");
                    locationLabel.setText("");
                    instructorsField.setText("");
                }
            }
        };
    }
}
