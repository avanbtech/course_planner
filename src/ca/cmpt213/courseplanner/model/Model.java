package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by faranakpouya on 2016-07-25.
 */
public class Model {

    private ArrayList<Course> allCourses;
    private Semester semester;
    private CampusLocation campusLocation;
    private String subject;
    private String catalogNumber;
    private boolean isGradAllowed;
    private boolean isUndergradAllowed;


    public Model(ArrayList<Course> courses){
        subject = "";
        semester = new Semester();
        campusLocation = new CampusLocation();
        this.allCourses = courses;
    }

    //This method is called by CourseListFilter to set subject, isGradAllowed and isUndergradAllowed
    public void setCourseListFilterData(String subject, boolean isGradAllowed, boolean isUndergradAllowed){
        this.subject = subject;
        this.isGradAllowed = isGradAllowed;
        this.isUndergradAllowed = isUndergradAllowed;
    }

    //This method is called by CourseList to set catalogNumber
    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    //This method is called by CourseOfferedBySemester to set semester and campus location
    public void setSemesterAndLocation(Semester semester, CampusLocation campusLocation){
        this.semester = semester;
        this.campusLocation = campusLocation;
    }

    //This method is called by CourseListFilter
    //Using Set to avoid duplicated department
    public Set<String> getDepartments(){
        Set<String> departments = new HashSet<>();
        for (Course aCourse : allCourses){
            departments.add(aCourse.getSubject());
        }
        return departments;
    }

    //If 2 session is offer in same location we should display once with all instructors


    // this method is called by CourseList to get all courses from specified department with certain criteria
    public Set<String> getCatalogNumbersInSpecificDepartment(){
        Set<String>catalogNumbersInSpecificDepartment = new HashSet<>();
        for (Course aCourse : allCourses){
            if (aCourse.getSubject().equals(subject)){
                if (isGradAllowed && !aCourse.getEducationLevel().isUndergrad()){
                    catalogNumbersInSpecificDepartment.add(aCourse.getCatalogNumber());
                }
                if (isUndergradAllowed && aCourse.getEducationLevel().isUndergrad()){
                    catalogNumbersInSpecificDepartment.add(aCourse.getCatalogNumber());
                }
            }
        }
        return catalogNumbersInSpecificDepartment;
    }

    public ArrayList<Semester> getSemestesrAndYears(){
        ArrayList<Semester> semesters = new ArrayList<>();
        for (Course aCourse : allCourses){
            if(aCourse.getSubject().equals(subject) && aCourse.getCatalogNumber().equals(catalogNumber)){
                semesters.add(aCourse.getSemester());
            }
        }
        return semesters;
    }

    //Return type is arrayList of numbers which represent number of times that the course offered in each semester respectively
    public Map<Semester.SemesterData, Integer> calculateNumberOfSemesters(){
        Map<Semester.SemesterData, Integer> OfferedInEachSemester = new HashMap<>();
        int offeredInSpring = 0;
        int offeredInSummer = 0;
        int offeredInFall = 0;
        for (Course aCourse : allCourses){
            if (aCourse.getSubject().equals(subject) && aCourse.getCatalogNumber().equals(catalogNumber)){
                Semester.SemesterData semester = aCourse.getSemester().getSemesterData();
                switch (semester){
                    case  SPRING:
                        offeredInSpring++;
                        break;
                    case SUMMER:
                        offeredInSummer++;
                        break;
                    case FALL:
                        offeredInFall++;
                        break;
                }
            }
        }
        OfferedInEachSemester.put(Semester.SemesterData.SPRING, offeredInSpring);
        OfferedInEachSemester.put(Semester.SemesterData.SUMMER, offeredInSummer);
        OfferedInEachSemester.put(Semester.SemesterData.FALL, offeredInFall);
        return OfferedInEachSemester;
    }

    //Return type is arrayList of numbers which represent number of times that the course offered in each campus location respectively
    public Map<CampusLocation.Locations, Integer>  calculateNumberOfOfferedInEachCampus(){
        Map<CampusLocation.Locations, Integer> OfferedInEachCampus = new HashMap<>();
        int offeredInBurnaby = 0;
        int offeredInSurrey = 0;
        int offeredInVancouver = 0;
        int offeredInOther = 0;
        for (Course aCourse : allCourses){
            if (aCourse.getSubject().equals(subject) && aCourse.getCatalogNumber().equals(catalogNumber)){
                CampusLocation.Locations location = aCourse.getCampusLocation().getLocation();
                switch (location){
                    case  BURNABY:
                        offeredInBurnaby++;
                        break;
                    case SURREY:
                        offeredInSurrey++;
                        break;
                    case HRBRCNTR:
                        offeredInVancouver++;
                        break;
                    default:
                        offeredInOther++;
                }
            }
        }
        OfferedInEachCampus.put(CampusLocation.Locations.BURNABY, offeredInBurnaby);
        OfferedInEachCampus.put(CampusLocation.Locations.SURREY, offeredInSurrey);
        OfferedInEachCampus.put(CampusLocation.Locations.HRBRCNTR, offeredInVancouver);
        OfferedInEachCampus.put(CampusLocation.Locations.OTHER, offeredInOther);
        return OfferedInEachCampus;
    }

    public Course getDetailInformation(){
        ArrayList<Course> sameCourses = new ArrayList<>();
        for (Course aCourse : allCourses){
            if (aCourse.getSubject().equals(subject) && aCourse.getCatalogNumber().equals(catalogNumber)
                    && aCourse.getSemester().getSemesterData() == semester.getSemesterData()
                    && aCourse.getSemester().getYear() == semester.getYear()
                    && aCourse.getCampusLocation().getLocation() == campusLocation.getLocation()){
                    sameCourses.add(aCourse);
                // Since the only difference between added courses is instructor name, instructors are combined and the second course
                // is removed from the list
                if (sameCourses.size() == 2){
                    String instructors = sameCourses.get(0).getInstructor() + ", " + sameCourses.get(1).getInstructor();
                    sameCourses.get(0).setInstructor(instructors);
                    sameCourses.remove(1);
                }
            }
        }
        return sameCourses.get(0);
    }
}
