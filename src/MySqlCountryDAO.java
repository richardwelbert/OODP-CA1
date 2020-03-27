import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCountryDAO implements CountryDAO{

    private DataSource db = DataSource.getInstance();


    @Override
    public ArrayList<Country> getCountries() {

        ArrayList<Country> countries = new ArrayList<Country>();

        String query = "SELECT * FROM country";
        ResultSet rs = db.select(query);
        String code = "";
        String name = "";
        Continent continent;
        double area;
        String head;
        Country c = null;

        try {

            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                continent = Continent.valueOf(rs.getString(3));;
                area = rs.getDouble(4);
                head = rs.getString(5);

                c = new Country.CountryBuilder(code, name, continent, area, head).build();
                countries.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;

    }

    @Override
    public Country findCountryById(String code) {

        String query = "SELECT * FROM country WHERE code = " + code + ";";
        ResultSet rs = db.select(query);

        String name = "";
        Continent continent;
        double area;
        String head;
        Country c = null;

        try {

            if (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                continent = Continent.valueOf(rs.getString(3));;
                area = rs.getDouble(4);
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

    @Override
    public Country findCountryByName(String name) {
        String query = "SELECT * FROM country WHERE code = " + name + ";";
        ResultSet rs = db.select(query);

        String code = "";
        Continent continent;
        double area;
        String head;
        Country c = null;

        try {

            if (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                continent = Continent.valueOf(rs.getString(3));;
                area = rs.getDouble(4);
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

    @Override
    public boolean saveCountry(Country country) {

        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        double area = country.getArea();
        String head = country.getHead();

        String query = "INSERT INTO country (code, name, continent, area, head) VALUES ('"+code+"', '"+name+"', '"+continent+"', '"+area+"', '"+head+"');";

        return db.save(query);
    }
}
