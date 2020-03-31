//This class has the DAO Pattern implemented.

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCountryDAO implements CountryDAO{

    //instance of the DataSource class that creates a connection with the database
    private DataSource db = DataSource.getInstance();


    //global variables to prevent writing the same code multiple times
    private String code = "";
    private String name = "";
    private Continent continent;
    private String cName;
    private float area;
    private String head;
    private Country c = null;
    private ResultSet rs;

    //Method that finds all countries in the database
    @Override
    public ArrayList<Country> getCountries() {

        ArrayList<Country> countries = new ArrayList<Country>();

        String query = "SELECT * FROM country";
        rs = db.getInstance().select(query);

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

                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                countries.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;

    }

    //Method that finds countries in the database with a specific code.
    @Override
    public Country findCountryById(String code) {

        String query = "SELECT * FROM country WHERE code = '" + code + "';";
        rs = db.getInstance().select(query);

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

                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                return c;
            }
            return null;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Method that finds a country in the database with an specific name.
    @Override
    public Country findCountryByName(String name) {
        String query = "SELECT * FROM country WHERE Name = '" + name + "';";
        rs = db.getInstance().select(query);

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

                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                return c;
            }
            return null;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Method that saves a new country in the database according to the input of the user.
    @Override
    public boolean saveCountry(Country country) {

        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        float area = country.getArea();
        String head = country.getHead();

        String query = "INSERT INTO country (Code, Name, Continent, SurfaceArea, HeadOfState) VALUES ('"+code+"', '"+name+"', '"+continent+"', '"+area+"', '"+head+"');";

        return db.getInstance().save(query);
    }
}
