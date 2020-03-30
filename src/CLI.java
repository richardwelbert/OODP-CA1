import java.util.ArrayList;

public class CLI {

    public static void main(String[] args){

        CountryDAO dao = new MySqlCountryDAO();

//        ArrayList<Country> countries = dao.getCountries();
//        System.out.println(countries);

//        Country c = dao.findCountryById("101");
//        System.out.println(c);

//        Country c = dao.findCountryByName("Brazil");
//        System.out.println(c);

        Country c = new Country.CountryBuilder ("ZZ1", "Zimbabue", Continent.Africa, 300, "Zi").build();
        dao.saveCountry(c);

    }


}
