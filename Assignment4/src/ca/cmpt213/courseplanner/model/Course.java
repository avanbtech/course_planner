package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

/**
 * Created by faranakpouya on 2016-07-23.
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
                  int enrolmentCapacity, int enrolmentTotal, String instructor, String componentCode, EducationLevel educationLevel){
        instructors = new ArrayList<>();
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.campusLocation = campusLocation;
        addInstructor(instructor);
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
    public String getInstructor(){
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

    public ComponentCodeCollection getComponentCodeCollection(){
        return componentCodeCollection;
    }

    // this method adds an instructor if it is not empty and if it has not been added to the list
    public void addInstructor(String instructor){
        if(instructor.length() == 0){
            return;
        }
        for(String aInstructor : instructors){
            if(aInstructor.equals(instructor)){
                return;
            }
        }
        instructors.add(instructor);
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
