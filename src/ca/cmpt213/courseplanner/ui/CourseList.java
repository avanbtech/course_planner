package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import javafx.scene.control.ComboBox;

import javax.swing.*;

/**
 * Created by faranakpouya on 2016-07-26.
 */
public class CourseList extends MyABCPanel {
    static String title = "Course List";

    public CourseList(Model model){
        super(model, title);
        ComboBox courseList = new ComboBox();
        //this.add(courseList);
    }
}
