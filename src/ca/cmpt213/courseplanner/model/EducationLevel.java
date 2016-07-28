package ca.cmpt213.courseplanner.model;

/**
 * Created by faranakpouya on 2016-07-23.
 */
public class EducationLevel {

    private boolean isUndergrad;

    public EducationLevel(){
        isUndergrad = true;
    }

    public EducationLevel(String str){
        readFromString(str);
    }

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
