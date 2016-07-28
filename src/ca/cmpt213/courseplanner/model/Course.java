package ca.cmpt213.courseplanner.model;

import sun.tools.tree.ShiftRightExpression;

/**
 * Created by faranakpouya on 2016-07-23.
 */
public class Course {
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private CampusLocation campusLocation;
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private String instructor;
    private String componentCode;
    private EducationLevel educationLevel;

    public Course(){
        semester = new Semester();
        educationLevel = new EducationLevel();
        campusLocation = new CampusLocation();
        subject = "";
        catalogNumber = "";
        enrolmentCapacity = 0;
        enrolmentTotal = 0;
        instructor = "";
        componentCode = "";
    }

    public Course(Semester semester, String subject, String catalogNumber, CampusLocation campusLocation,
                  int enrolmentCapacity, int enrolmentTotal, String instructor, String componentCode, EducationLevel educationLevel){
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.campusLocation = campusLocation;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructor = instructor;
        this.componentCode = componentCode;
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
        return instructor;
    }

    public String getComponentCode(){
        return componentCode;
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

    public void setInstructor(String instructor){
        this.instructor = instructor;
    }

    public void setComponentCode(String componentCode){
        this.componentCode = componentCode;
    }
}
