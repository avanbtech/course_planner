package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

/**
 * @author  Faranak Nobakhtian
 * @version      2016-07-23.
 * Course class has all required fields to describe a course.
 */
public class Course implements Comparable<Course>{
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private CampusLocation campusLocation;
    private ArrayList<String> instructors;
    private ComponentCodeCollection componentCodeCollection;
    private EducationLevel educationLevel;

    public Course(Semester semester, String subject, String catalogNumber, CampusLocation campusLocation,
                  int enrolmentCapacity, int enrolmentTotal, String[] instructors, String componentCode, EducationLevel educationLevel){
        this.instructors = new ArrayList<>();
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.campusLocation = campusLocation;
        addInstructors(instructors);
        this.componentCodeCollection = new ComponentCodeCollection(componentCode, enrolmentTotal, enrolmentCapacity);
        this.educationLevel = educationLevel;
    }

    public Semester getSemester(){
        return semester;
    }

    public EducationLevel getEducationLevel(){
        return educationLevel;
    }

    public CampusLocation getCampusLocation(){
        return campusLocation;
    }

    public String getSubject(){
        return subject;
    }

    public String getCatalogNumber(){
        return catalogNumber;
    }

    // this method returns list of all instructures for this course
    public String getInstructorText(){
        String instructorName = "";
        int index = 0;
        for (String aInstructor : instructors){
            if(index > 0){
                instructorName += ", ";
            }
            instructorName += aInstructor;
            index++;
        }
        return instructorName;
    }

    public ArrayList<String> getInstructors(){
        return instructors;
    }

    public ComponentCodeCollection getComponentCodeCollection(){
        return componentCodeCollection;
    }

    // this method adds an instructor if it is not empty and if it has not been added to the list
    public void addInstructors(String[] instructors){
        for(String aInstructor : instructors){
            aInstructor = aInstructor.trim();       // removing spaces
            if(aInstructor.length() == 0){
                continue;
            }
            boolean found = false;
            // see if instructor is already added
            for(String aInstructor2 : this.instructors){
                if(aInstructor2.equals(aInstructor)){
                    found = true;
                    break;
                }
            }
            if(!found){     // add instructor if it is not in the list
                this.instructors.add(aInstructor);
            }
        }
    }

    // courses are compared based on following sequence: subject, catalog number, course type, campus location
    public int compareTo(Course other){
        int i = this.subject.compareTo(other.subject);
        if (i != 0){
            return i;
        }

        i = this.catalogNumber.compareTo(other.catalogNumber);
        if (i != 0){
            return i;
        }

        i = this.semester.getOriginalCode().compareTo(other.semester.getOriginalCode());
        if (i != 0){
            return i;
        }

        return this.campusLocation.toString().compareTo(other.campusLocation.toString());
    }

    public void addCapacity(String componentCode, int enrolmentTotal, int enrolmentCapacity){
        this.componentCodeCollection.addCapacity(componentCode, enrolmentTotal, enrolmentCapacity);
    }

}
