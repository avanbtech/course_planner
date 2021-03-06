package ca.cmpt213.courseplanner.model;

/**
 * @author  Faranak Nobakhtian
 * @version  2016-07-23.
 * Semester class holds information about term and year of a course.
 * This class also converts a string to term and year.
 */
public class Semester {

    public enum SemesterData{
        SPRING,
        FALL,
        SUMMER,
        UNKNOWN
    }

    private final int START_YEAR = 2000;
    private int year;
    private SemesterData semester;
    String originalCode;


    public Semester(){
        semester = SemesterData.UNKNOWN;
    }

    public Semester(String str){
        readFromString(str);
        year = convertToYear(str);
        originalCode = str;
    }

    public int getYear(){
        return year;
    }

    public String getOriginalCode(){
        return originalCode;
    }

    public SemesterData getSemesterData(){
        return semester;
    }

    private int convertToYear(String str){
        String yearPart = str.substring(1, 3);
        int number = Integer.parseInt(yearPart);
        year = number + START_YEAR;
        return year;
    }

    private void readFromString(String str){
        semester = SemesterData.UNKNOWN;
        if(str == null || str.length() != 4){
            return;
        }
        if(str.charAt(3) == '7'){
            semester = SemesterData.FALL;
        }
        else if(str.charAt(3) == '4'){
            semester = SemesterData.SUMMER;
        }
        else if(str.charAt(3) == '1'){
            semester = SemesterData.SPRING;
        }
    }

    @Override
    public String toString(){
        if(semester == SemesterData.FALL){
            return "FALL";
        }
        else if(semester == SemesterData.SPRING){
            return "SPRING";
        }
        else if(semester == SemesterData.SUMMER){
            return "SUMMER";
        }
        return "UNKNOWN";
    }
}
