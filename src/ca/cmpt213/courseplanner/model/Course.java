package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

/**
 * Created by faranakpouya on 2016-07-23.
 */
public class Course implements Comparable<Course>{
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private CampusLocation campusLocation;
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;
    private ComponentCodeCollection componentCodeCollection;
    private EducationLevel educationLevel;

    public Course(){
        semester = new Semester();
        educationLevel = new EducationLevel();
        campusLocation = new CampusLocation();
        subject = "";
        catalogNumber = "";
        instructors = new ArrayList<>();
        componentCodeCollection = new ComponentCodeCollection();
    }

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

//    public int getYear(){
//        return this.convertToYear(getSemester().toString());
//    }

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

    public int getEnrolmentCapacity(){
        return enrolmentCapacity;
    }

    public int getEnrolmentTotal(){
        return enrolmentTotal;
    }

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

    public void setSemester(Semester semester){
        this.semester = semester;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setEducationLevel(EducationLevel educationLevel){
        this.educationLevel = educationLevel;
    }

    public void setCampusLocation(CampusLocation campusLocation){
        this.campusLocation = campusLocation;
    }

    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    public void setEnrolmentCapacity(int enrolmentCapacity){
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public void setEnrolmentTotal(int enrolmentTotal){
        this.enrolmentTotal = enrolmentTotal;
    }

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

        return i = this.campusLocation.toString().compareTo(other.campusLocation.toString());
    }

    public void addCapacity(String componentCode, int enrolmentTotal, int enrolmentCapacity){
        this.componentCodeCollection.addCapacity(componentCode, enrolmentTotal, enrolmentCapacity);
    }

}
