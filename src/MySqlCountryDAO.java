//This class has the DAO Pattern implemented.

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCountryDAO implements CountryDAO{

    //Instance of the DataSource class that creates a connection with the database
    private DataSource db = DataSource.getInstance();


    //Global variables to prevent writing the same code multiple times
    private String code = "";
    private String name = "";
    private Continent continent;
    private String cName;
    private float area;
    private String head;
    private Country c = null;
    private ResultSet rs;
    ArrayList<Country> countries;

    //Method that finds all countries in the database
    @Override
    public ArrayList<Country> getCountries() {

        //Creating an ArrayList of Countries
        countries = new ArrayList<Country>();

        //Query passed to the Database
        String query = "SELECT * FROM country";
        rs = db.getInstance().select(query);

        //Looping over the Result Set
        try {

            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                cName = rs.getString(3).replace(" ", "_");
                if(cName.isEmpty()){
                    continue;
                }
                continent = Continent.valueOf(cName);
                area = rs.getFloat(4);
                head = rs.getString(5);

                //Creating new objects of Country type
                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                //Adding the countries inside the ArrayList
                countries.add(c);

                //Closing the connection with the DB
                db.closing();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;

    }

    //Method that finds countries in the database with an specific code.
    @Override
    public Country findCountryById(String code) {

        //Query passed to the Database
        String query = "SELECT * FROM country WHERE code = '" + code + "';";
        rs = db.getInstance().select(query);

        //Looping over the Result Set
        try {

            if (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                cName = rs.getString(3).replace(" ", "_");
                if(cName.isEmpty()){
                    return null;
                }
                continent = Continent.valueOf(cName);
                area = rs.getFloat(4);
                head = rs.getString(5);

                //Creating new objects of Country type
                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                return c;
            }
            //Closing the connection with the DB
            db.closing();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Method that finds a country in the database with an specific name.
    @Override
    public ArrayList<Country> findCountryByName(String name) {

        //Creating an ArrayList of Countries
        countries = new ArrayList<Country>();

        //Query passed to the Database
        String query = "SELECT * FROM country WHERE Name = '" + name + "';";
        rs = db.getInstance().select(query);

        //Looping over the Result Set
        try {

            while (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                cName = rs.getString(3).replace(" ", "_");
                if(cName.isEmpty()){
                    continue;
                }
                continent = Continent.valueOf(cName);
                area = rs.getFloat(4);
                head = rs.getString(5);

                //Creating new objects of Country type
                c = new Country.CountryBuilder(code, name, continent, area, head).build();

                //Adding the countries inside the ArrayList
                countries.add(c);
            }
            //Closing the connection with the DB
            db.closing();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return countries;
    }

    //Method that saves a new country in the database according to the input of the user.
    @Override
    public boolean saveCountry(Country country) {

        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        float area = country.getArea();
        String head = country.getHead();

        //Query passed to the Database
        String query = "INSERT INTO country (Code, Name, Continent, SurfaceArea, HeadOfState) VALUES ('"+code+"', '"+name+"', '"+continent+"', '"+area+"', '"+head+"');";

        boolean save = db.getInstance().save(query);

        //Closing the connection with the DB
        db.closing();

        return save;
    }
}
