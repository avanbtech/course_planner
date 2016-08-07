package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class DetailsOfCourseOffering extends MyABCPanel{
    static String title = "Details of Course Offering";
    JPanel userContentsPanel;
    JLabel courseNameLabel;
    JLabel semesterLabel;
    JLabel locationLabel;
    JTextArea instructorsField;

    public DetailsOfCourseOffering(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setVgap(0);
        userContentsPanel.setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        userContentsPanel.add(makeTopSection(width));
    }

    private JPanel makeTopSection(int width){
        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BorderLayout());
        JPanel internalPanel = new JPanel();
        internalPanel.setLayout(new GridLayout(4, 2, 0, 0));
        internalPanel.add(new JLabel("Course Name:"));
        internalPanel.add(makeCourseNameLabel());
        internalPanel.add(new JLabel("Semester:"));
        internalPanel.add(makeSemesterLabel());
        internalPanel.add(new JLabel("Location:"));
        internalPanel.add(makeLocationLabel());
        internalPanel.add(new JLabel("Instructors:"));
        internalPanel.add(makeInstructorsField());
        internalPanel.setPreferredSize(new Dimension(width, internalPanel.getPreferredSize().height));
        topSectionPanel.add(internalPanel, BorderLayout.PAGE_START);
        return topSectionPanel;
    }

    private JPanel makeCourseNameLabel(){
        JPanel panel = makeWhitePanel();
        courseNameLabel = new JLabel("");
        panel.add(courseNameLabel);
        return panel;
    }

    private JPanel makeSemesterLabel(){
        JPanel panel = makeWhitePanel();
        semesterLabel = new JLabel("");
        panel.add(semesterLabel);
        return panel;
    }

    private JPanel makeLocationLabel(){
        JPanel panel = makeWhitePanel();
        locationLabel = new JLabel("");
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
}
