package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

/**
 * Created by faranakpouya on 2016-07-23.
 */
public abstract class MyABCPanel extends JPanel{
    private Model model;
    private JPanel userContentsPanel;

    public MyABCPanel(Model model, String title){
        this.model = model;
        this.setLayout(new BorderLayout());
        userContentsPanel = new JPanel();
        userContentsPanel.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.LOWERED,
                Color.black, Color.gray));
        this.add(userContentsPanel, BorderLayout.CENTER);
        displayTitlePanel(title);
    }

    protected JPanel getUserContentsPanel(){
        return userContentsPanel;
    }

    protected Model getModel(){
        return model;
    }

    protected void displayTitlePanel(String title){
        JLabel label = new JLabel(title);
        this.add(label, BorderLayout.NORTH);
    }
}
