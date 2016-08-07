package ca.cmpt213.courseplanner.model;
import ca.cmpt213.courseplanner.ui.*;

import java.util.ArrayList;

/**
 * Created by faranakpouya on 2016-07-24.
 */
public class Main {

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        ReadData data = new ReadData();
        ArrayList<Course> courses = data.readAllLinesOfFile();
        Model model = new Model(courses);
        model.dumpModel();
        new Frame(model);
    }
}
