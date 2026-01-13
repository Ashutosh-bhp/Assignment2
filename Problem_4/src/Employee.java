import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private String name;
    private String email;
    private String phone;
    private double salary;
    private String department;

    private static final String NAME_PATTERN="^[a-zA-Z\\s]{2,50}$";
    private static final String EMAIL_PATTERN="^[a-zA-Z0-9.%+-_]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_PATTERN="^[6-9]\\d{9}$";
    private static final String DEPARTMENT_PATTERN="[a-zA-Z\\s]{2,30}$";

    public Employee(String name, String email, String phone, String department, double salary){
        if(!isValidName(name)){
            throw new IllegalArgumentException("Invalid name! employee name should contain only letters and spaces (2-50 characters)");
        }
        if(!isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email format!");
        }
        if(!isValidPhone(phone)){
            throw new IllegalArgumentException("Invalid phone number format! Phone number should be of 10 digits starting with 6-9");
        }
        if(!isValidDept(department)){
            throw new IllegalArgumentException("Invalid department! Department should contain only letters and spaces (2,30 characters)");
        }
        if(salary<0){
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.name=name.trim();
        this.email=email.trim();
        this.phone=phone.trim();
        this.department=department.trim();
        this.salary=salary;
    }

    private boolean isValidDept(String department) {
        if(department==null || department.trim().isEmpty()) return false;
        Pattern patter=Pattern.compile(DEPARTMENT_PATTERN);
        Matcher matcher= patter.matcher(department);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {
        if(phone==null || phone.trim().isEmpty()) return false;
        Pattern patter=Pattern.compile(PHONE_PATTERN);
        Matcher matcher= patter.matcher(phone);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        if(email==null || email.trim().isEmpty()) return false;
        Pattern patter=Pattern.compile(EMAIL_PATTERN);
        Matcher matcher= patter.matcher(email);
        return matcher.matches();
    }

    private boolean isValidName(String name) {
        if(name==null || name.trim().isEmpty()) return false;
        Pattern patter=Pattern.compile(NAME_PATTERN);
        Matcher matcher= patter.matcher(name);
        return matcher.matches();
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
    @Override
    public String toString() {
        return String.format("%-20s %-30s %-15s %-20s ₹%.2f",
                name, email, phone, department, salary);
    }
    public void displayDetails(){
        System.out.println("\n==========EMPLOYEE DETAILS============");
        System.out.println("    NAME        :     "+name);
        System.out.println("    EMAIL       :     "+email);
        System.out.println("    PHONE       :     "+phone);
        System.out.println("    DEPARTMENT  :     "+department);
        System.out.println("    SALARY      :     "+salary);
    }
}
