import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private int rollNo;
    private String name;
    private String email;
    private String phone;
    private double cgpa;

    private static final String NAME_PATTERN="^[a-zA-Z\\s]{2,50}$";
    private static final String EMAIL_PATTERN="^[a-zA-Z0-9.%_+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_PATTERN="^[6-9]\\d{9}$";
    Student(int rollNo, String name, String email, String phone, double cgpa   ){
            if(rollNo<=0){
                throw new IllegalArgumentException("Roll number must be positive");
            }
            if(!isValidName(name)){
                throw new IllegalArgumentException("Invalid Name! Name should contain only only letters and spaces (2-50 characters).");
            }
            if(!isValidEmail(email)){
                throw new IllegalArgumentException("Invalid Email format !");

            }
            if(!isValidPhone(phone)){
                throw new IllegalArgumentException("Invalid Phone number format! Must be 10 digits starting with 6-9.");
            }
            if(cgpa<=0.0 || cgpa>=10.0){
                throw new IllegalArgumentException("CGPA must be between 0 and 10.");
            }
            this.rollNo=rollNo;
            this.name=name;
            this.email=email;
            this.phone=phone;
            this.cgpa=cgpa;
    }

    private static boolean isValidPhone(String phone) {
        if(phone==null || phone.trim().isEmpty()) return false;
        Pattern pattern=Pattern.compile(PHONE_PATTERN);
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }

    private static boolean isValidEmail(String email) {
        if(email==null|| email.trim().isEmpty()) return false;
        Pattern pattern=Pattern.compile(EMAIL_PATTERN);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidName(String name) {
        if(name==null || name.trim().isEmpty()) return false;
        Pattern pattern=Pattern.compile(NAME_PATTERN);
        Matcher matcher=pattern.matcher(name.trim());
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public double getCgpa() {
        return cgpa;
    }
    @Override
    public String toString() {
        return String.format("%-10d %-20s %-30s %-15s %.2f",
                rollNo, name, email, phone, cgpa);
    }
    public void displayDetails(){
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    STUDENT DETAILS                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Roll No    : %-49d ║%n", rollNo);
        System.out.printf("║ Name       : %-49s ║%n", name);
        System.out.printf("║ Email      : %-49s ║%n", email);
        System.out.printf("║ Phone      : %-49s ║%n", phone);
        System.out.printf("║ CGPA       : %-49.2f ║%n", cgpa);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}
