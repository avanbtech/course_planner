package ca.cmpt213.courseplanner.model;

/**
 * Created by Faranak Nobakhtian on 2016-07-23.
 * EducationLevel class holds educational level of a course (undergraduate or graduate)
 */
public class EducationLevel {

    private boolean isUndergrad;

    public EducationLevel(String str){
        readFromString(str);
    }

    // this method reads educational level of a course from a string
    // a course is an undergraduate course unless its starting number is larger than 4
    private void readFromString(String str){
        int courseNumber = getStringValue(str);
        if(courseNumber >= 500){
            isUndergrad = false;
        }
        else{
            isUndergrad = true;
        }
    }

    private int getStringValue(String str) {
        return Integer.valueOf("0" + str.replaceAll("(\\d*).*", "$1"));
    }

    @Override
    public String toString(){
        if(isUndergrad){
            return "Undergraduate";
        }
        return "Graduate";
    }

    public boolean isUndergrad(){
        return isUndergrad;
    }
}
