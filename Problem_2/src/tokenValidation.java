
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tokenValidation {

    private static String MOBILE_PATTERN="^[6-9]\\d{9}$";
    private static String MAIL_PATTERN="^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.+-]+\\.[a-zA-Z]{2,}$";
    private static String USERNAME_PATTERN="^[a-zA-Z][a-zA-Z0-9@_]{4,14}$";
    private static String PASSWORD_PATTERN="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&]).{8,}$";
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n----Validation Menu----");
            System.out.println("1. Validate Mobile Number");
            System.out.println("2. Validate Email ID");
            System.out.println("3. Validate USERNAME");
            System.out.println("4. Validate Password");
            System.out.println("5. Exit");
            try {

                System.out.println("Enter your choice: ");
                choice=sc.nextInt();
                switch (choice){
                    case 1:
                        validateMobileNo(sc);
                        break;
                    case 2:
                        validateEmail(sc);
                        break;
                    case 3:
                        validateUserName(sc);
                        break;
                    case 4:
                        validatePassword(sc);
                        break;
                    case 5:
                        System.out.println("\n Exiting the program....");
                        break;
                    default:
                        System.out.println("\n Invalid input! please enter a number between 1-5 : ");

                }
//                if(choice!=5){
//                    System.out.println("Press Enter to continue...");
//                    sc.nextLine();
//                }
                }catch (Exception e){
            System.out.println("\n Invalid input! please enter a valid number between 1-5. ");
            sc.nextLine();
            choice=0;
            }
        } while (choice!=5);
        sc.close();
    }

    private static void validatePassword(Scanner sc) {
        System.out.println("Rules: Min 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char (@#$%^&+=)");
        System.out.println("Enter your password: ");
        try {
            String password=sc.next().trim();
            Pattern pattern= Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher=pattern.matcher(password);
            boolean matchFound=matcher.find();
            if(matchFound){
                System.out.println("Valid password");
            }
            else
                System.out.println("Not a valid password");
        } catch (Exception e) {
            System.out.println("Error occurred while validation "+e.getMessage());
        }
    }

    private static void validateUserName(Scanner sc) {
        System.out.println("Rules: 5-15 characters, start with letter, only letters/numbers/underscore");
        System.out.println("Enter User Name ");
        try {
            String userName=sc.next().trim();
            Pattern pattern= Pattern.compile(USERNAME_PATTERN);
            Matcher matcher=pattern.matcher(userName);
            boolean matchFound=matcher.find();
            if (matchFound){
                System.out.println("Valid User Name");
            }
            else
                System.out.println("Not a Valid User Name ");
        }catch (Exception e){
            System.out.println("Error occurred while validation "+ e.getMessage());
        }
    }

    private static void validateEmail(Scanner sc) {
        System.out.println("Rules: Valid email format (e.g., user@example.com)");
        System.out.println("Enter your Email ID  ");
        try {
            String email=sc.next().trim();
            Pattern pattern= Pattern.compile(MAIL_PATTERN);
            Matcher matcher= pattern.matcher(email);
            boolean matchFound=matcher.find();
            if(matchFound){
                System.out.println("\n Valid Email ID ");
            }
            else
                System.out.println("Not a valid email ");
        }catch (Exception e){
            System.out.println("Error occurred while validation "+e.getMessage());
        }
    }

    private static void validateMobileNo(Scanner sc) {
        System.out.println("Rules: 10 digits, starting with 6-9");
        System.out.println("Enter you Mobile Number: ");
        try {
            String mobile=sc.next().trim();
            Pattern pattern=Pattern.compile(MOBILE_PATTERN);
            Matcher matcher= pattern.matcher(mobile);
            boolean matchFound=matcher.find();
            if(matchFound){
                System.out.println("\n Valid Mobile number ");
            }
            else {
                System.out.println("\n Not a valid Mobile number ");
            }
        }catch (Exception e){
            System.out.println("\n Error occurred while validation "+e.getMessage());
        }
    }
}
