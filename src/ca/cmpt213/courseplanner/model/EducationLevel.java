package ca.cmpt213.courseplanner.model;

/**
 * Created by faranakpouya on 2016-07-23.
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
        isUndergrad = true;
        if(str == null || str.length() == 0){
            return;
        }
        try{
            if(Integer.parseInt(Character.toString(str.charAt(0))) > 4){
                isUndergrad = false;
            }
        }
        catch (Exception e){
            isUndergrad = true;
        }
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
