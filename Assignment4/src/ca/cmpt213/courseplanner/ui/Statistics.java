package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class Statistics extends MyABCPanel {
    static String title = "Statistic";
    JPanel userContentsPanel;

    public Statistics(Model model, int width){
        super(model, title);
        userContentsPanel = getUserContentsPanel();
        userContentsPanel.setOpaque(true);
        userContentsPanel.setBackground(Color.white);
        userContentsPanel.setLayout(new BoxLayout(userContentsPanel, BoxLayout.PAGE_AXIS));
        //setPreferredSize(new Dimension(width, 50));
        //userContentsPanel.setPreferredSize(new Dimension(width, userContentsPanel.getPreferredSize().height));
        userContentsPanel.setPreferredSize(new Dimension(width, 200));
    }
}
