//This class has the Builder pattern implemented.

public class Country {

    //Private variables
    private String code;
    private String name;
    private Continent continent;
    private float area;
    private String head;

    //Defining the constructor
    private Country(CountryBuilder builder){
        this.code = builder.code;
        this.name = builder.name;
        this.continent = builder.continent;
        this.area = builder.area;
        this.head = builder.head;
    }

    //Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }

    public float getArea() {
        return area;
    }

    public String getHead() {
        return head;
    }

    //Specific implementation of the Country
    public String toString(){
        return "Country Code: " + code + ", " +
                "Name: " + name + ", " +
                "Continent: " + continent + ", " +
                "Surface Area: " + area + ", " +
                "Head Of State: " + head + " \n";
    }

    //Inner builder class
    public static class CountryBuilder {

        private String code;
        private String name;
        private Continent continent;
        private float area;
        private String head;

        //Builder constructor
        public CountryBuilder(String code, String name, Continent continent, float area, String head){
            this.code = code;
            this.name = name;
            this.continent = continent;
            this.area = area;
            this.head = head;
        }

        public Country build(){
            return new Country(this);
        }

    }
}
