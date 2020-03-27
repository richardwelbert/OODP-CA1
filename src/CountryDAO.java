import java.util.ArrayList;

public interface CountryDAO {

    public ArrayList<Country> getCountries();
    public Country findCountryById(String code);
    public Country findCountryByName (String name);
    public boolean saveCountry(Country country);

}
