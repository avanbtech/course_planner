package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class CourseOfferingBySemester extends MyABCPanel{

    private static final int INTERNAL_SPACING_X = 50;
    private static final int INTERNAL_SPACING_Y = 20;
    private static final int NUMBER_OF_SEMESTERS = 3;
    static String title = "Course Offering by Semester";
    JPanel userContentsPanel;
    ArrayList<Course> allSections;
    private int firstYear;
    private int lastYear;

    public CourseOfferingBySemester(Model model){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setLayout(new GridBagLayout());
        userContentsPanel.setOpaque(true);
        userContentsPanel.setBackground(Color.white);
        model.registerSelectedCourseListeners(makeSelectdCourseListener());
        allSections = getModel().getAllSectionsOfSpecificCourse();
        addContent();
        this.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
    }

    private SelectedCourseListener makeSelectdCourseListener(){
        return new SelectedCourseListener() {
            @Override
            public void courseSelected() {
                ArrayList<Semester> semesters = getModel().getSemestesrAndYears();
                String selectedCatalogNumber = getModel().getCatalogNumber();
                allSections = getModel().getAllSectionsOfSpecificCourse();
                addContent();
            }
        };
    }

    private void addContent(){
        userContentsPanel.removeAll();
        if(allSections.size() == 0){
            JLabel centerLabel = new JLabel("Use a filter to select a course.");
            userContentsPanel.add(centerLabel);
            userContentsPanel.updateUI();
            return;
        }
        int numberOfYears = getNumberOfYears();
        for (int x = 0; x < NUMBER_OF_SEMESTERS + 1; x++){
            for (int y = 0; y < numberOfYears + 1; y++){
                GridBagConstraints c = makeContains(x, y);
                if (x == 0 && y == 0){
                    continue;
                }
                else if (x == 0){
                    JLabel label = new JLabel(makeYearLabels(y));
                    userContentsPanel.add(label, c);
                }
                else if (y == 0){
                    JLabel label = new JLabel(makeSemesterInString(x));
                    userContentsPanel.add(label, c);
                }
                else{
                    JPanel cellPanel = makePanel(x, y);
                    userContentsPanel.add(cellPanel, c);
                }
            }
        }
        userContentsPanel.updateUI();
    }

    private GridBagConstraints makeContains(int x, int y){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        if (x == 0){
            c.ipadx = 10;
            c.ipady = INTERNAL_SPACING_Y;
        }
        else if (y == 0){
            c.ipadx = INTERNAL_SPACING_X;
            c.ipady = 10;
        }
        else{
            c.ipadx = INTERNAL_SPACING_X;
            c.ipady = INTERNAL_SPACING_Y;
        }
        if (x == 0 || y == 0){
            c.weightx = 0;
            c.weighty = 0;
        }
        else{
            c.weightx = 1;
            c.weighty = 1;
        }
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private JPanel makePanel(int x, int y){
        String semester = makeSemesterInString(x);
        ArrayList<Course> coursesInSpecificYearAndSemester = coursesInSpecificYearAndSemester(firstYear + y - 1, semester);
        JPanel cellPanel = new JPanel();
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        cellPanel.setLayout(new BorderLayout());
        JPanel internalPanel = new JPanel(new GridLayout(0,1,0,0));
        for (Course aCourse : coursesInSpecificYearAndSemester){
            internalPanel.add(makeButton(aCourse));
        }
        cellPanel.add(internalPanel, BorderLayout.NORTH);
        return cellPanel;
    }

    private JButton makeButton(Course course){
        JButton cellButton = new JButton(makeTitleOfButton(course));
        cellButton.addActionListener(e -> {
            getModel().setCourse(course);
        });
        return cellButton;
    }

    private String makeTitleOfButton(Course course){
        String subject = course.getSubject();
        String catalogNumber = course.getCatalogNumber();
        String location = course.getCampusLocation().toString();
        return subject + " " + catalogNumber + " - " + location;
    }

    private ArrayList<Course> coursesInSpecificYearAndSemester(int year, String semester){
        ArrayList<Course> coursesInSpecificYearAndSemester = new ArrayList<>();
        for (Course aCourse : allSections){
            if (aCourse.getSemester().getYear() == year && aCourse.getSemester().toString().equalsIgnoreCase(semester)){
                coursesInSpecificYearAndSemester.add(aCourse);
            }
        }
        return coursesInSpecificYearAndSemester;
    }

    private String makeSemesterInString(int x){
        String semester;
        if (x == 1){
            semester = "Spring";
        }
        else if (x == 2){
            semester = "Summer";
        }
        else if (x == 3){
            semester = "Fall";
        }
        else {
            semester = "";
        }
        return semester;
    }

    private int getNumberOfYears(){
        firstYear = allSections.get(0).getSemester().getYear();
        lastYear = allSections.get(allSections.size() - 1).getSemester().getYear();
        int numberOfYears = lastYear - firstYear + 1;
        return numberOfYears;
    }

    private String makeYearLabels(int y){
        int year = firstYear + y - 1;
        return "" + year;
    }


}
