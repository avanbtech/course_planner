package ca.cmpt213.courseplanner.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Created by faranakpouya on 2016-07-24.
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
                if(instructor.equals("(null)")){
                    instructor = "";
                }
                Course course = new Course(semester, courseInfo[1], courseInfo[2], campusLocation, enrolmentCapacity,
                        enrolmentTotal, instructor, courseInfo[7], educationLevel);
                boolean isSameCourse = false;

                for (Course aCourse : courses){
                    if (aCourse.getSubject().equals(course.getSubject()) && aCourse.getCatalogNumber().equals(course.getCatalogNumber())
                            && aCourse.getSemester().getSemesterData() == semester.getSemesterData()
                            && aCourse.getSemester().getYear() == semester.getYear()
                            && aCourse.getCampusLocation().getLocation() == campusLocation.getLocation()){
                        aCourse.addInstructor(course.getInstructor());
                        aCourse.addCapacity(courseInfo[7], enrolmentTotal, enrolmentCapacity);
                        isSameCourse = true;
                        break;
                    }
                }
                if (!isSameCourse){
                    courses.add(course);
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return courses;
    }
}
