package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class DetailsOfCourseOffering extends MyABCPanel{
    static String title = "Details of Course Offering";
    JPanel userContentsPanel;

    public DetailsOfCourseOffering(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setOpaque(true);
        userContentsPanel.setBackground(Color.white);
        userContentsPanel.setPreferredSize(new Dimension(width, 80));
    }
}
