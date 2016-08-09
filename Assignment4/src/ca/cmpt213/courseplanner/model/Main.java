package ca.cmpt213.courseplanner.model;
import ca.cmpt213.courseplanner.ui.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

/**
 * @author  Faranak Nobakhtian
 * @version  2016-07-24.
 */
public class Main {

    private static String filePath = "./data/course_data_2016.csv";

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        File file = new File(filePath);
        ArrayList<Course> courses;
        try{
            ReadData data = new ReadData();
            courses = data.readAllLinesOfFile(file);
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Data file (" + file.getAbsolutePath() + ") not found.");
            return;
        }
        Model model = new Model(courses);
        model.dumpModel();
        new Frame(model);
    }
}
