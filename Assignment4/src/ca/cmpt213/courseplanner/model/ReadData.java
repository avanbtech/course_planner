package ca.cmpt213.courseplanner.model;

import javax.swing.text.html.HTMLDocument;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by faranakpouya on 2016-07-24.
 * ReadData class reads data file and puts all courses into an array
 */
public class ReadData {

    ArrayList<Course> courses;

    public ReadData(){
        courses = new ArrayList<Course>();
    }
    public ArrayList<Course> readAllLinesOfFile(){
        File sourceFile = new File("./data/course_data_2016.csv");
        try{
            Scanner scanner = new Scanner(sourceFile);
            scanner.nextLine();     //To skip first line, which is header.
            while (scanner.hasNextLine()){
                String text = scanner.nextLine();
                String[] courseInfo;
                courseInfo = text.split(",");
                Semester semester = new Semester(courseInfo[0]);
                CampusLocation campusLocation = new CampusLocation(courseInfo[3]);
                int enrolmentCapacity = Integer.parseInt(courseInfo[4]);
                int enrolmentTotal = Integer.parseInt(courseInfo[5]);
                EducationLevel educationLevel = new EducationLevel(courseInfo[2]);
                String instructor = courseInfo[6];
                if(instructor.equals("(null)")){        // converting null to empty for instructor
                    instructor = "";
                }
                Course course = new Course(semester, courseInfo[1], courseInfo[2], campusLocation, enrolmentCapacity,
                        enrolmentTotal, instructor, courseInfo[7], educationLevel);
                courses.add(course);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        removeDuplicateCourses();
        return courses;
    }

    // this method combines all courses that have the same subject, catalog number, semester, year, and campus location
    private void removeDuplicateCourses(){
        Collections.sort(courses);
        int i = 0;
        int maxIndex = courses.size();
        while(i < maxIndex - 1){
            int j = i + 1;
            Course aCourse = courses.get(i);
            while(j < maxIndex){
                Course course = courses.get(j);
                if(aCourse.getSubject().equals(course.getSubject()) && aCourse.getCatalogNumber().equals(course.getCatalogNumber())
                        && aCourse.getSemester().getSemesterData() == course.getSemester().getSemesterData()
                        && aCourse.getSemester().getYear() == course.getSemester().getYear()
                        && aCourse.getCampusLocation().getLocation() == course.getCampusLocation().getLocation())
                {
                    aCourse.addInstructor(course.getInstructor());
                    ComponentCodeCollection componentCodes = course.getComponentCodeCollection();
                    if(componentCodes.size() != 0){
                        ComponentCode code = componentCodes.getComponentsData().get(0);
                        aCourse.addCapacity(code.toString(), code.getEnrolmentTotal(), code.getEnrolmentCapacity());
                    }
                    courses.remove(j);
                    maxIndex = courses.size();
                    continue;
                }
                break;
            }
            i = j;
        }
    }
}
