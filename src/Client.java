import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

public class Client {

    CountryDAO dao = new MySqlCountryDAO();
    ArrayList<Country> countries;
    Country c;

    public Client(){

        menu();
    }

    //method to read input from the user
    public String readingFromUser(){
        Scanner sc = new Scanner( System.in );
        String input = sc.nextLine();
        return input;
    }

    //Main menu
    public void menu(){

        System.out.println("Hello! Welcome to the World System! \n" +
                "Please, enter one of the options below:\n\n" +
                "1- List ALL Countries in the World System; \n" +
                "2- Find a Country by Country Code; \n" +
                "3- Find a Country by Country Name; \n" +
                "4- Save a new Country; \n" +
                "5- Exit.\n");
        subMenu();
    }

    //Submenu
    public void subMenu() {

        String input = readingFromUser();
        if(input.equals("1")) {

            listAllCountries();
        }
        else if(input.equals("2")) {

            findByCode();
        }
        else if(input.equals("3")) {

            findByName();
        }
        else if(input.equals("4")) {

            saveNewCountry();
        }
        else if(input.equals("5")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

    }

    private void listAllCountries(){
        System.out.println("Here's a list of ALL Countries!\n");
        countries = dao.getCountries();
        System.out.println(countries);
        menu();
    }

    private void findByCode(){

        System.out.println("Find a Country by Country Code!");
        System.out.println("Please tell me the Country Code: \n" +
                "(It shouldn't be more than 3 characters!) \n");
        String input = readingFromUser();

        if (input.length() <= 3){
            c = dao.findCountryById(input);
            System.out.println("Here is the Country requested:");
            System.out.println(c);
            menu();
        }
        else {
            System.out.println("I'm sorry, this is Code is invalid.\n" +
                    "Please remember the Country Code shouldn't exceed 3 characters!\n" +
                    "Redirecting you to the main menu.\n\n");
            menu();
        }
    }

    private void findByName(){

        System.out.println("Find a Country by Country Name!");
        System.out.println("Please tell me the Country Name: \n");

        String input = readingFromUser();
        countries = dao.findCountryByName(input);

        System.out.println("Here is the Country requested:");
        System.out.println(countries);
        menu();
    }

    private Country saveNewCountry (){

        System.out.println("Save a New Country! \n");
        System.out.println("Please type a Code for this new Country:" +
                "(It shouldn't be more than 3 characters!)");
        String input = readingFromUser();

        if (input.length() > 3){
            System.out.println("I'm sorry, this is Code is invalid.\n" +
                    "Please remember the Country Code shouldn't exceed 3 characters!\n" +
                    "Redirecting you to the main menu.\n\n");
            menu();
        }
        System.out.println("Now type a Name for this new Country:");
        String input2 = readingFromUser();

        System.out.println("And a Continent that matches those options:\n" +
                "'Asia', 'Europe', 'North America', 'Africa', 'Oceania', 'Antarctica' or 'South America'\n" +
                "Please note the system is case-sensitive.");
        String input3 = readingFromUser();

        if (enumIsValid(input3)){

            Continent con = Continent.valueOf(input3);

            System.out.println("Now type a Surface Area for this new Country:");
            float input4 = Float.parseFloat(readingFromUser());

            System.out.println("And to finish, type the Country's Head Of State:");
            String input5 = readingFromUser();

            Country c = new Country.CountryBuilder (input, input2, con, input4, input5).build();
            dao.saveCountry(c);
            menu();
        } else {
            System.out.println("Sorry, this Continent is not valid.\n" +
                    "Redirecting you to the main menu.\n\n");
            input3 = "";
            menu();
        }

        return c;
    }

    private boolean enumIsValid(String enumName){

        EnumSet<Continent> except = EnumSet.of(Continent.Europe, Continent.Africa, Continent.Antarctica, Continent.Asia, Continent.North_America, Continent.Oceania, Continent.South_America);

        boolean valid;
        try {
            valid = except.contains(Continent.valueOf(enumName));
        } catch (IllegalArgumentException e) { valid = false; }
        System.out.println(valid ? "valid" : "invalid");
        return valid;
    }

}
