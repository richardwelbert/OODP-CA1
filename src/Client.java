import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    CountryDAO dao = new MySqlCountryDAO();

    public Client(){

        menu();
    }

    //method to read input from the user
    public String readingFromUser(){
        Scanner sc = new Scanner( System.in );
        String input = sc.nextLine();
        return input;
    }

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

    public void subMenu() {

        String input = readingFromUser();
        if(input.equals("1")) {
            System.out.println("Here's a list of ALL Countries!\n");
            ArrayList<Country> countries = dao.getCountries();
            System.out.println(countries);
            menu();
        }
        else if(input.equals("2")) {
            System.out.println("Find a Country by Country Code!");
            System.out.println("Please tell me the Country Code: \n" +
                    "(It shouldn't be more than 3 characters!) \n");
            String input2 = readingFromUser();

            if (input2.length() <= 3){
                Country c = dao.findCountryById(input2);
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
        else if(input.equals("3")) {
            System.out.println("Find a Country by Country Name!");
            System.out.println("Please tell me the Country Name: \n");
            String input3 = readingFromUser();
            Country c = dao.findCountryByName(input3);
            System.out.println("Here is the Country requested:");
            System.out.println(c);
            menu();
        }
//        else if(input.equals("4")) {
//            System.out.println("Save a New Country! \n");
//            System.out.println("Please tell me the Country Name: \n");
//            String input3 = readingFromUser();
//            Country c = dao.findCountryByName(input3);
//            System.out.println("Here is the Country requested: \n");
//            System.out.println(c);
//        }
        else if(input.equals("5")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

    }

}
