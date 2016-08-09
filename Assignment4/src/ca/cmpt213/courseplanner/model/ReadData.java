package ca.cmpt213.courseplanner.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author  Faranak Nobakhtian
 * @version  2016-07-24.
 * ReadData class reads data file and puts all courses into an array
 */
public class ReadData {

    ArrayList<Course> courses;

    public ReadData(){
        courses = new ArrayList<>();
    }
    public ArrayList<Course> readAllLinesOfFile(File sourceFile) throws FileNotFoundException{
        Scanner scanner = new Scanner(sourceFile);
        scanner.nextLine();     //To skip first line, which is header.
        while (scanner.hasNextLine()){
            String text = scanner.nextLine();
            ArrayList<Integer> indices = findAllOccurences(text, '"');
            String instructor = "";
            if(indices.size() != 0){
                if(indices.size() != 2){
                    throw new RuntimeException("Input file is not in valid format");
                }
                // only instructor names can be within quotations
                instructor = text.substring(indices.get(0)+1, indices.get(1));
                // replacing instructor name with ABCD for correct splitting
                text = text.substring(0, indices.get(0)) + "ABCD" + text.substring(indices.get(1)+1,text.length());
            }
            String[] courseInfo;
            courseInfo = text.split(",");
            Semester semester = new Semester(courseInfo[0]);
            CampusLocation campusLocation = new CampusLocation(courseInfo[3]);
            int enrolmentCapacity = Integer.parseInt(courseInfo[4]);
            int enrolmentTotal = Integer.parseInt(courseInfo[5]);
            EducationLevel educationLevel = new EducationLevel(courseInfo[2]);
            if(indices.size() == 0) {   // if there is no quotation, 7th element in instructor
                instructor = courseInfo[6];
            }
            String[] instructors = instructor.split(",");   // for the case where multiple instructors exist
            if(instructor.equals("(null)")){        // converting null to empty for instructor
                instructors = new String[0];
            }
            Course course = new Course(semester, courseInfo[1], courseInfo[2], campusLocation, enrolmentCapacity,
                    enrolmentTotal, instructors, courseInfo[7], educationLevel);
            courses.add(course);
        }
        scanner.close();

        removeDuplicateCourses();
        return courses;
    }

    // this method finds all occurrences of chracter in text
    private ArrayList<Integer> findAllOccurences(String text, char chracter){
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < text.length(); i++){
            if(text.charAt(i) == chracter){
                indices.add(i);
            }
        }
        return indices;
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
                    aCourse.addInstructors(course.getInstructors().toArray(new String[0]));
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
