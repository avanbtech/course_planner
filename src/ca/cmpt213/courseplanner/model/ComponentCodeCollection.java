package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by faranakpouya on 2016-08-05.
 */
public class ComponentCodeCollection {
    ArrayList<ComponentCode> componentsData;

    public ComponentCodeCollection(String str, int enrolmentTotal, int capacity){
        componentsData = new ArrayList<>();
        componentsData.add(new ComponentCode(str, enrolmentTotal, capacity));
    }

    public ComponentCodeCollection(){
        componentsData = new ArrayList<>();
    }

    public void addCapacity(String componentCode, int enrolmentTotal, int capacity){
        for(ComponentCode aComponentCode : componentsData){
            if(aComponentCode.isSame(componentCode)){
                aComponentCode.addCapacity(enrolmentTotal, capacity);
                return;
            }
        }
        componentsData.add(new ComponentCode(componentCode, enrolmentTotal, capacity));
    }

    public ArrayList<ComponentCode> getComponentsData(){
        return componentsData;
    }

    public int size(){
        return componentsData.size();
    }
}
