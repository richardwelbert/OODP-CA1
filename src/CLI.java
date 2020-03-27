import java.util.ArrayList;

public class CLI {

    public static void main(String[] args){

        CountryDAO dao = new MySqlCountryDAO();

        ArrayList<Country> countries = dao.getCountries();
        System.out.println(countries);

    }


}
