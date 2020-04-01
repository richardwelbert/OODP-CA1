/****************************************************************************
 * Code adapted from the structure provided by our Lecutrer
 *Title: Week5.2/src/CustomerDAO.java
 *Author: Amilcar Aponte
 *Date: 01/04/2020 (last accessed)
 *Availability: https://moodle.cct.ie/course/view.php?id=1505
 ****************************************************************************/

//This class is the Interface DAO API as required for the DAO Pattern.

import java.util.ArrayList;

public interface CountryDAO {

    //Method that returns all the countries in the DB
    public ArrayList<Country> getCountries();

    //Method that returns a country by the specific code
    public Country findCountryById(String code);

    //Method that returns all countries with the specific name
    public ArrayList<Country> findCountryByName (String name);

    //Method that creates a new country and saves into the DB
    public boolean saveCountry(Country country);

}
