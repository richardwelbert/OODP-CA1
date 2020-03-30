public enum Continent {

    Asia ("Asia"),
    Europe ("Europe"),
    North_America ("North America"),
    Africa ("Africa"),
    Oceania ("Oceania"),
    Antarctica ("Antarctica"),
    South_America ("South America");

    private String displayName;

    Continent (String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String toString(){
        return displayName;
    }

}
