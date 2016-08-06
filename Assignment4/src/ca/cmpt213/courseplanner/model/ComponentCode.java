package ca.cmpt213.courseplanner.model;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.Collection;

/**
 * Created by faranakpouya on 2016-07-23.
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

    public void addCapacity(int enrolmentTotal, int enrolmentCapacity){
        this.enrolmentCapacity += enrolmentCapacity;
        this.enrolmentTotal += enrolmentTotal;
    }

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
