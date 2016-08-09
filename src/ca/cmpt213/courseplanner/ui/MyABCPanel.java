package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Faranak Nobakhtian on 2016-08-01.
 * This class is parent of all panels and it displays panel title and a border around contents
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
