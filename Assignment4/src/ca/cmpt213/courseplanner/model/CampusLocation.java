package ca.cmpt213.courseplanner.model;

/**
 * Created by faranakpouya on 2016-07-23.
 */
public class CampusLocation {

    public enum Locations{
        SURREY,
        BURNABY,
        HRBRCNTR,
        OTHER
    }

    Locations campusLocation;

    public CampusLocation(){
        campusLocation = Locations.OTHER;
    }

    public CampusLocation(String str){
        readFromString(str);
    }

    public Locations getLocation(){
        return campusLocation;
    }

    private void readFromString(String str){
        campusLocation = Locations.OTHER;
        if(str == null){
            return;
        }
        if(str.equalsIgnoreCase("surrey")){
            campusLocation = Locations.SURREY;
        }
        else if(str.equalsIgnoreCase("burnaby")){
            campusLocation = Locations.BURNABY;
        }
        else if(str.equalsIgnoreCase("HRBRCNTR")){
            campusLocation = Locations.HRBRCNTR;
        }
    }

    @Override
    public String toString(){
        if(campusLocation == Locations.SURREY){
            return "SURREY";
        }
        else if(campusLocation == Locations.BURNABY){
            return "BURNABY";
        }
        else if(campusLocation == Locations.HRBRCNTR){
            return "HRBRCNTR";
        }
        return "OTHER";
    }

    public static String[] getAvailableLocations(){
        String[] locations = new String[4];
        locations[0] = "Bby";
        locations[1] = "Sry";
        locations[2] = "Van";
        locations[3] = "Other";
        return locations;
    }
}
