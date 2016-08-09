package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.bargraph.BarGraphModel;
import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.courseplanner.model.CampusLocation;
import ca.cmpt213.courseplanner.model.Semester;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import ca.cmpt213.bargraph.BarGraphIcon;

/**
 * @author  Faranak Nobakhtian
 * @version  2016-08-01.
 * This class displays statistics for the selected course
 */
public class Statistics extends MyABCPanel {
    private static final int NUMBER_OF_SEMESTER_BARS = 3;
    private static final int NUMBER_OF_CAMPUS_BARS = 4;
    private static final int BAR_GRAPH_HEIGHT = 200;
    static String title = "Statistic";
    JPanel userContentsPanel;
    BarGraphIcon semesterOfferingBarGraphIcon;
    BarGraphModel semesterOfferingBarGraphModel;
    BarGraphIcon campusOfferingBarGraphIcon;
    BarGraphModel campusOfferingBarGraphModel;
    JLabel courseNameLabel;

    public Statistics(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;
        c.gridx = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.ipady = 0;
        //userContentsPanel.setLayout(new BoxLayout(userContentsPanel, BoxLayout.PAGE_AXIS));
        userContentsPanel.setLayout(new GridBagLayout());
        userContentsPanel.add(makeCourseNameLabel(), c);
        userContentsPanel.add(makeSemesterOfferingPanel(width), c);
        userContentsPanel.add(makeCampusOfferingPanel(width), c);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        model.registerSelectedCourseListeners(makeSelectdCourseListener());
    }

    private JPanel makeCourseNameLabel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        courseNameLabel = new JLabel("Course:");
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(courseNameLabel);
        return panel;
    }

    private JPanel makeSemesterOfferingPanel(int width){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Semester Offerings:");
        panel.add(label);
        int[] semesterData = new int[NUMBER_OF_SEMESTER_BARS];
        for(int i = 0; i < NUMBER_OF_SEMESTER_BARS; i++){
            semesterData[i] = 0;
        }
        String[] semesterLabels = getAvailableSemesters();
        semesterOfferingBarGraphModel = new BarGraphModel(semesterData, semesterLabels);
        semesterOfferingBarGraphIcon = new BarGraphIcon(semesterOfferingBarGraphModel, width, BAR_GRAPH_HEIGHT);
        JLabel barPlotLabel = new JLabel(semesterOfferingBarGraphIcon);
        panel.add(barPlotLabel);
        return panel;
    }

    private JPanel makeCampusOfferingPanel(int width){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Campus Offerings:");
        panel.add(label);
        int[] campusData = new int[NUMBER_OF_CAMPUS_BARS];
        for(int i = 0; i < NUMBER_OF_CAMPUS_BARS; i++){
            campusData[i] = 0;
        }
        String[] campusLabels = getAvailableLocations();
        campusOfferingBarGraphModel = new BarGraphModel(campusData, campusLabels);
        campusOfferingBarGraphIcon = new BarGraphIcon(campusOfferingBarGraphModel, width, BAR_GRAPH_HEIGHT);
        JLabel barPlotLabel = new JLabel(campusOfferingBarGraphIcon);
        panel.add(barPlotLabel);
        return panel;
    }

    private SelectedCourseListener makeSelectdCourseListener(){
        return new SelectedCourseListener() {
            @Override
            public void courseSelected() {
                updateBarGraphs();
            }
        };
    }

    private void updateBarGraphs(){
        courseNameLabel.setText("Course: " + getModel().getSubject() + " " + getModel().getCatalogNumber());

        Map<CampusLocation.Locations, Integer> campusOfferingData = getModel().calculateNumberOfOfferedInEachCampus();
        int[] campusData = new int[NUMBER_OF_CAMPUS_BARS];
        campusData[0] = campusOfferingData.get(CampusLocation.Locations.BURNABY);
        campusData[1] = campusOfferingData.get(CampusLocation.Locations.SURREY);
        campusData[2] = campusOfferingData.get(CampusLocation.Locations.HRBRCNTR);
        campusData[3] = campusOfferingData.get(CampusLocation.Locations.OTHER);
        campusOfferingBarGraphModel.setData(campusData);

        Map<Semester.SemesterData, Integer> semesterOfferingData = getModel().calculateNumberOfSemesters();
        int[] semesterData = new int[NUMBER_OF_SEMESTER_BARS];
        semesterData[0] = semesterOfferingData.get(Semester.SemesterData.SPRING);
        semesterData[1] = semesterOfferingData.get(Semester.SemesterData.SUMMER);
        semesterData[2] = semesterOfferingData.get(Semester.SemesterData.FALL);
        semesterOfferingBarGraphModel.setData(semesterData);

        this.updateUI();
    }

    private String[] getAvailableLocations(){
        String[] locations = new String[4];
        locations[0] = "Bby";
        locations[1] = "Sry";
        locations[2] = "Van";
        locations[3] = "Other";
        return locations;
    }
    private String[] getAvailableSemesters(){
        String[] semesters = new String[3];
        semesters[0] = "Spring";
        semesters[1] = "Summer";
        semesters[2] = "Fall";
        return semesters;
    }

}
