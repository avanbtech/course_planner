package ca.cmpt213.courseplanner.model;

import ca.cmpt213.courseplanner.ui.CourseListListener;
import ca.cmpt213.courseplanner.ui.SelectedCourseListener;
import java.io.*;

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
    private ArrayList<CourseListListener> courseListListeners;
    private ArrayList<SelectedCourseListener> selectedCourseListeners;


    public Model(ArrayList<Course> courses){
        subject = "";
        semester = new Semester();
        campusLocation = new CampusLocation();
        this.allCourses = courses;
        courseListListeners = new ArrayList<>();
        selectedCourseListeners = new ArrayList<>();
        // sorting code data
        for(Course aCourse : courses){
            Collections.sort(aCourse.getComponentCodeCollection().getComponentsData());
        }
    }

    public String getSubject(){
        return subject;
    }

    public String getCatalogNumber(){
        return catalogNumber;
    }

    //This method is called by CourseListFilter to set subject, isGradAllowed and isUndergradAllowed
    public void setCourseListFilterData(String subject, boolean isGradAllowed, boolean isUndergradAllowed){
        if(this.subject.equals(subject) && this.isGradAllowed == isGradAllowed && this.isUndergradAllowed == isUndergradAllowed){
            return;
        }
        this.subject = subject;
        this.isGradAllowed = isGradAllowed;
        this.isUndergradAllowed = isUndergradAllowed;
        catalogNumber = null;
        callCourseListListeners();
        callSelectedCourseListeners();
    }

    private void callCourseListListeners(){
        for (CourseListListener listener : courseListListeners){
            listener.updateCourseList();
        }
    }

    //This method is called by CourseList to set catalogNumber
    public void setCatalogNumber(String catalogNumber){
        if(this.catalogNumber.equals(catalogNumber)){
            return;
        }
        this.catalogNumber = catalogNumber;
        callSelectedCourseListeners();
    }

    private void callSelectedCourseListeners(){
        for (SelectedCourseListener listener : selectedCourseListeners){
            listener.courseSelected();
        }
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

    public ArrayList<Course> getAllSectionsOfSpecificCourse(){
        if(catalogNumber == null){
            return new ArrayList<>();
        }
        ArrayList<Course> allSections = new ArrayList<>();
        for (Course aCourse : allCourses){
            if (aCourse.getSubject().equals(subject) && aCourse.getCatalogNumber().equals(catalogNumber)){
                allSections.add(aCourse);
            }
        }
        return allSections;
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
            }
        }
        return sameCourses.get(0);   //Need to be Changed.
    }

    public void registerCourseListListener(CourseListListener courseListListener){
        courseListListeners.add(courseListListener);
    }

    public void registerSelectedCourseListeners(SelectedCourseListener selectedCourseListener){
        selectedCourseListeners.add(selectedCourseListener);
    }

    public void dumpModel(){
        Course course = allCourses.get(0);
        String subject = "";
        String catalogNumber = "";
        String semester = "";
        String campusLocation = "";
        File targetFile = new File("./data/output_dump.txt");
        try{
            PrintWriter writer = new PrintWriter(targetFile);
            for (Course aCourse : allCourses){
                if (!subject.equals(aCourse.getSubject()) || !catalogNumber.equals(aCourse.getCatalogNumber())) {
                    // course subject or catalog number has changed, need to show title
                    writer.println("\n" + aCourse.getSubject() + " " + aCourse.getCatalogNumber());
                    subject = aCourse.getSubject();
                    catalogNumber = aCourse.getCatalogNumber();
                }
                if (!semester.equals(aCourse.getSemester().getOriginalCode()) || !campusLocation.equals(aCourse.getCampusLocation().toString())) {
                    // course semester or campusLocation has changed, need to show the course
                    String originalCode = aCourse.getSemester().getOriginalCode();
                    writer.println("\t" + originalCode + " in " + aCourse.getCampusLocation() + " by " + aCourse.getInstructor());
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
            writer.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
