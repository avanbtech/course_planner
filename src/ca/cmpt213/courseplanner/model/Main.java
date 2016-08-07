package ca.cmpt213.courseplanner.model;
import ca.cmpt213.courseplanner.ui.*;

import java.util.ArrayList;

/**
 * Created by faranakpouya on 2016-07-24.
 */
public class Main {

    public static void main(String[] args){
        ReadData data = new ReadData();
        ArrayList<Course> courses = data.readAllLinesOfFile();
        Model model = new Model(courses);
        model.dumpModel();
        Frame frame = new Frame(model);
        //frame.designFrame();
        //ArrayList<String> departments = model.getDepartments();
    }
}
