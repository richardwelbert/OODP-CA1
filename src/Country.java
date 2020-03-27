public class Country {

    //private variables
    private String code;
    private String name;
    private Continent continent;
    private double area;
    private String head;

    //defining the constructor
    private Country(CountryBuilder builder){
        this.code = builder.code;
        this.name = builder.name;
        this.continent = builder.continent;
        this.area = builder.area;
        this.head = builder.head;
    }

    //getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }

    public double getArea() {
        return area;
    }

    public String getHead() {
        return head;
    }

    //inner builder class
    public static class CountryBuilder {

        private String code;
        private String name;
        private Continent continent;
        private double area;
        private String head;

        //builder constructor
        public CountryBuilder(String code, String name, Continent continent, double area, String head){
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
