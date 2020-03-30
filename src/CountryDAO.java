//This class is the Interface DAO API as required for the DAO Pattern.

import java.util.ArrayList;

public interface CountryDAO {

    public ArrayList<Country> getCountries();
    public Country findCountryById(String code);
    public Country findCountryByName (String name);
    public boolean saveCountry(Country country);

}
