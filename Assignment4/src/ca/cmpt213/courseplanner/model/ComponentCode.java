package ca.cmpt213.courseplanner.model;


/**
 * @author  Faranak Nobakhtian
 * @version  2016-07-23.
 * ComponentCode class holds type of course (LEC, TUT, etc.) and its enrolment capacity and enrolment total
 */
public class ComponentCode implements Comparable<ComponentCode>{

    String codeType;
    private int enrolmentCapacity;
    private int enrolmentTotal;

    public ComponentCode(String codeType, int enrolmentTotal, int enrolmentCapacity){
        this.codeType = codeType.toUpperCase();
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
    }

    public int getEnrolmentCapacity(){
        return enrolmentCapacity;
    }

    public int getEnrolmentTotal(){
        return enrolmentTotal;
    }

    // this method increases enrolment capacity and enrolment total of the code
    public void addCapacity(int enrolmentTotal, int enrolmentCapacity){
        this.enrolmentCapacity += enrolmentCapacity;
        this.enrolmentTotal += enrolmentTotal;
    }

    // this code shows if given code is the same as this object
    public boolean isSame(String codeType){
        return this.codeType.equals(codeType.toUpperCase());
    }

    @Override
    public String toString(){
        return codeType;
    }

    @Override
    public int compareTo(ComponentCode other){
        return this.codeType.compareTo(other.codeType);
    }
}
