package ca.cmpt213.courseplanner.model;

import ca.cmpt213.courseplanner.ui.CourseDetailListener;
import ca.cmpt213.courseplanner.ui.CourseListListener;
import ca.cmpt213.courseplanner.ui.SelectedCourseListener;
import java.io.*;
import java.util.*;

/**
 * Created by Faranak Nobakhtian on 2016-07-25.
 * Model class implements the main logic of the program.
 * This class holds all changes made by user, for example, changing subject, catalog number, or course
 * Also, Model class informs observers of all changes happening in other UI panels
 */
public class Model {

    //private ArrayList<Course> allCourses;
    private Map<String, ArrayList<Course>> allCourses;
    private Semester semester;
    private CampusLocation campusLocation;
    private String subject;
    private String catalogNumber;
    private boolean isGradAllowed;
    private boolean isUndergradAllowed;
    private String instructor;
    private ArrayList<CourseListListener> courseListListeners;
    private ArrayList<SelectedCourseListener> selectedCourseListeners;
    private ArrayList<CourseDetailListener> courseDetailListeners;
    private Course selectedCourse;


    public Model(ArrayList<Course> courses){
        subject = "";
        semester = new Semester();
        campusLocation = new CampusLocation();
        courseListListeners = new ArrayList<>();
        selectedCourseListeners = new ArrayList<>();
        courseDetailListeners = new ArrayList<>();
        instructor = "";
        // sorting code data
        for(Course aCourse : courses){
            Collections.sort(aCourse.getComponentCodeCollection().getComponentsData());
        }
        convertCoursesToMap(courses);
    }

    // to improve performance of the program, courses are stored in a map that connects subjects to courses in the department
    private void convertCoursesToMap(ArrayList<Course> courses){
        this.allCourses = new HashMap<>();
        String subject = "";
        ArrayList<Course> currentCourses = new ArrayList<>();
        for(Course aCourse : courses){
            if(!aCourse.getSubject().equals(subject)){
                currentCourses = new ArrayList<>();
                subject = aCourse.getSubject();
                this.allCourses.put(subject, currentCourses);
            }
            currentCourses.add(aCourse);
        }
    }

    public String getSubject(){
        return subject;
    }

    public String getCatalogNumber(){
        return catalogNumber;
    }

    public Semester getSemester(){
        return semester;
    }

    public CampusLocation getCampusLocation(){
        return campusLocation;
    }

    public String getInstructor(){
        return instructor;
    }

    //This method is called by CourseListFilter to set subject, isGradAllowed and isUndergradAllowed
    public void setCourseListFilterData(String subject, boolean isGradAllowed, boolean isUndergradAllowed){
        if(this.subject.equals(subject) && this.isGradAllowed == isGradAllowed && this.isUndergradAllowed == isUndergradAllowed){
            return;
        }
        this.subject = subject;
        this.isGradAllowed = isGradAllowed;
        this.isUndergradAllowed = isUndergradAllowed;
        callCourseListListeners();
    }

    private void callCourseListListeners(){
        catalogNumber = null;
        for (CourseListListener listener : courseListListeners){
            listener.updateCourseList();
        }
        callSelectedCourseListeners();
    }

    //This method is called by CourseList to set catalogNumber
    public void setCatalogNumber(String catalogNumber){
        if(this.catalogNumber != null && this.catalogNumber.equals(catalogNumber)){  // avoid updating observers if same catalog number is selected
            return;
        }
        this.catalogNumber = catalogNumber;
        callSelectedCourseListeners();
    }

    private void callSelectedCourseListeners(){
        selectedCourse = null;
        for (SelectedCourseListener listener : selectedCourseListeners){
            listener.courseSelected();
        }
        callCourseDetailsListener();
    }

    private void callCourseDetailsListener(){
        for (CourseDetailListener listener : courseDetailListeners){
            listener.updateDetailsOfCourse();
        }
    }

    public void setCourse(Course course){
        if(course == this.selectedCourse){  // avoid updating observers if same course is selected
            return;
        }
        this.selectedCourse = course;
        callCourseDetailsListener();
    }

    public Course getCourse(){
        return selectedCourse;
    }

    //This method is called by CourseListFilter
    //Using Set to avoid duplicated department
    public Set<String> getDepartments(){
        Set<String> departments = new HashSet<>(allCourses.keySet());
        return departments;
    }

    // this method is called by CourseList to get all courses from specified department with certain criteria
    public Set<String> getCatalogNumbersInSpecificDepartment(){
        Set<String>catalogNumbersInSpecificDepartment = new HashSet<>();
        ArrayList<Course> courses = allCourses.get(subject);
        for (Course aCourse : courses){
            if (isGradAllowed && !aCourse.getEducationLevel().isUndergrad()){
                catalogNumbersInSpecificDepartment.add(aCourse.getCatalogNumber());
            }
            if (isUndergradAllowed && aCourse.getEducationLevel().isUndergrad()){
                catalogNumbersInSpecificDepartment.add(aCourse.getCatalogNumber());
            }
        }
        return catalogNumbersInSpecificDepartment;
    }

    // this method is called by CourseOfferingBySemester to get available sections for the selected course
    public ArrayList<Course> getAllSectionsOfSpecificCourse(){
        if(catalogNumber == null){
            return new ArrayList<>();
        }
        ArrayList<Course> allSections = new ArrayList<>();
        ArrayList<Course> courses = allCourses.get(subject);
        for (Course aCourse : courses){
            if (aCourse.getCatalogNumber().equals(catalogNumber)){
                allSections.add(aCourse);
            }
        }
        return allSections;
    }

    //Return type is arrayList of numbers which represent number of times that the course offered in each semester respectively
    public Map<Semester.SemesterData, Integer> calculateNumberOfSemesters(){
        Map<Semester.SemesterData, Integer> OfferedInEachSemester = new HashMap<>();
        ArrayList<Course> courses = allCourses.get(subject);
        int offeredInSpring = 0;
        int offeredInSummer = 0;
        int offeredInFall = 0;
        for (Course aCourse : courses){
            if (aCourse.getCatalogNumber().equals(catalogNumber)){
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
        ArrayList<Course> courses = allCourses.get(subject);
        int offeredInBurnaby = 0;
        int offeredInSurrey = 0;
        int offeredInVancouver = 0;
        int offeredInOther = 0;
        for (Course aCourse : courses){
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

    public void registerCourseListListener(CourseListListener courseListListener){
        courseListListeners.add(courseListListener);
    }

    public void registerSelectedCourseListeners(SelectedCourseListener selectedCourseListener){
        selectedCourseListeners.add(selectedCourseListener);
    }

    public void registerCourseDetailsListener(CourseDetailListener courseDetailListener){
        courseDetailListeners.add(courseDetailListener);
    }
    public void deregisterCourseListListener(CourseListListener courseListListener){
        int index = 0;
        for(CourseListListener aCourseListListener : courseListListeners){

            if(courseListListener == aCourseListListener){
                courseListListeners.remove(index);
            }
            index++;
        }
    }

    public void deregisterSelectedCourseListeners(SelectedCourseListener selectedCourseListener){
        int index = 0;
        for(SelectedCourseListener aSelectedCourseListener : selectedCourseListeners){
            if(selectedCourseListener == aSelectedCourseListener){
                selectedCourseListeners.remove(index);
            }
            index++;
        }
    }

    // this method writes all courses data to output file
    public void dumpModel(){
        String subject = "";
        String catalogNumber = "";
        String semester = "";
        String campusLocation = "";
        File targetFile = new File("./data/output_dump.txt");
        try{
            PrintWriter writer = new PrintWriter(targetFile);
            for(String newSubject : allCourses.keySet()){
                ArrayList<Course> courses = allCourses.get(newSubject);
                for (Course aCourse : courses){
                    if (!subject.equals(aCourse.getSubject()) || !catalogNumber.equals(aCourse.getCatalogNumber())) {
                        // course subject or catalog number has changed, need to show title
                        writer.println("\n" + aCourse.getSubject() + " " + aCourse.getCatalogNumber());
                        subject = aCourse.getSubject();
                        catalogNumber = aCourse.getCatalogNumber();
                    }
                    if (!semester.equals(aCourse.getSemester().getOriginalCode()) || !campusLocation.equals(aCourse.getCampusLocation().toString())) {
                        // course semester or campusLocation has changed, need to show the course
                        String originalCode = aCourse.getSemester().getOriginalCode();
                        writer.println("\t" + originalCode + " in " + aCourse.getCampusLocation() + " by " + aCourse.getInstructorText());
                        semester = aCourse.getSemester().getOriginalCode();
                        campusLocation = aCourse.getCampusLocation().toString();
                    }
                    ArrayList<ComponentCode> codes = aCourse.getComponentCodeCollection().getComponentsData();
                    for(ComponentCode aComponentCode : codes){
                        writer.println("\t\t" + "Type " + aComponentCode.toString() +
                                ", Enrollment = " + aComponentCode.getEnrolmentTotal() + "/" +
                                aComponentCode.getEnrolmentCapacity());
                    }

                }
            }
            writer.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
