import java.util.*;
import java.util.regex.*;


interface StudentOperations {
    void addStudent(Student student);
    void displayAllStudents();
    void removeStudentById(String id);
    Student searchStudentById(String id);
    void sortStudentsByMarks();
}


class Student implements Comparable<Student> {
    private String studentId;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(String studentId, String name, String email, String course, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }


    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }


    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.marks, this.marks); // Descending order
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-20s | Email: %-25s | Course: %-15s | Marks: %.2f",
                studentId, name, email, course, marks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}


class InvalidStudentIdException extends Exception {
    public InvalidStudentIdException(String message) {
        super(message);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}


class UniversityManagementSystem implements StudentOperations {

    private ArrayList<Student> studentList;


    private Vector<Student> studentVector;


    private Stack<Student> removedStudents;


    private List<Student> studentLinkedList;


    private Set<String> uniqueCourses;


    private HashMap<String, Student> studentHashMap;


    private Hashtable<String, Double> studentMarks;


    private TreeMap<String, Student> sortedStudentMap;


    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String STUDENT_ID_PATTERN = "^STU\\d{4}$";

    private Pattern emailPattern;
    private Pattern studentIdPattern;

    public UniversityManagementSystem() {
        studentList = new ArrayList<>();
        studentVector = new Vector<>();
        removedStudents = new Stack<>();
        studentLinkedList = new LinkedList<>();
        uniqueCourses = new HashSet<>();
        studentHashMap = new HashMap<>();
        studentMarks = new Hashtable<>();
        sortedStudentMap = new TreeMap<>();

        emailPattern = Pattern.compile(EMAIL_PATTERN);
        studentIdPattern = Pattern.compile(STUDENT_ID_PATTERN);
    }


    private boolean validateStudentId(String studentId) {
        Matcher matcher = studentIdPattern.matcher(studentId);
        return matcher.matches();
    }


    private boolean validateEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void addStudent(Student student) {
        try {

            if (!validateStudentId(student.getStudentId())) {
                throw new InvalidStudentIdException(
                        "Invalid Student ID format. Expected format: STU#### (e.g., STU0001)");
            }


            if (!validateEmail(student.getEmail())) {
                throw new InvalidEmailException(
                        "Invalid email format. Please provide a valid email address.");
            }


            if (studentHashMap.containsKey(student.getStudentId())) {
                throw new DuplicateStudentException(
                        "Student with ID " + student.getStudentId() + " already exists!");
            }


            if (student.getMarks() < 0 || student.getMarks() > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100");
            }


            studentList.add(student);
            studentVector.add(student);
            studentLinkedList.add(student);
            studentHashMap.put(student.getStudentId(), student);
            studentMarks.put(student.getStudentId(), student.getMarks());
            sortedStudentMap.put(student.getStudentId(), student);
            uniqueCourses.add(student.getCourse());

            System.out.println("✓ Student added successfully!");

        } catch (InvalidStudentIdException | InvalidEmailException |
                 DuplicateStudentException | IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    @Override
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.println("ALL STUDENTS (Total: " + studentList.size() + ")");
        System.out.println("=".repeat(120));

        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("=".repeat(120));
    }

    @Override
    public void removeStudentById(String id) {
        try {
            if (!studentHashMap.containsKey(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " not found!");
            }

            Student student = studentHashMap.get(id);


            studentList.remove(student);
            studentVector.remove(student);
            studentLinkedList.remove(student);
            studentHashMap.remove(id);
            studentMarks.remove(id);
            sortedStudentMap.remove(id);


            removedStudents.push(student);

            System.out.println("✓ Student removed successfully!");

        } catch (StudentNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    @Override
    public Student searchStudentById(String id) {
        try {
            if (!studentHashMap.containsKey(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " not found!");
            }

            Student student = studentHashMap.get(id);
            System.out.println("\n" + "=".repeat(120));
            System.out.println("STUDENT FOUND:");
            System.out.println("=".repeat(120));
            System.out.println(student);
            System.out.println("=".repeat(120));
            return student;

        } catch (StudentNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void sortStudentsByMarks() {
        if (studentList.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        Collections.sort(studentList);

        System.out.println("\n" + "=".repeat(120));
        System.out.println("STUDENTS SORTED BY MARKS (Descending Order)");
        System.out.println("=".repeat(120));

        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("%2d. %s%n", (i + 1), studentList.get(i));
        }
        System.out.println("=".repeat(120));
    }


    public void convertHashMapToTreeMap() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("HASHMAP TO TREEMAP CONVERSION (Sorted by Student ID)");
        System.out.println("=".repeat(120));

        TreeMap<String, Student> convertedMap = new TreeMap<>(studentHashMap);

        for (Map.Entry<String, Student> entry : convertedMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("=".repeat(120));
    }


    public void countStudentsCourseWise() {
        if (studentList.isEmpty()) {
            System.out.println("No students to count.");
            return;
        }

        Map<String, Integer> courseCount = new HashMap<>();

        for (Student student : studentList) {
            courseCount.put(student.getCourse(),
                    courseCount.getOrDefault(student.getCourse(), 0) + 1);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COURSE-WISE STUDENT COUNT");
        System.out.println("=".repeat(60));

        for (Map.Entry<String, Integer> entry : courseCount.entrySet()) {
            System.out.printf("%-30s : %d students%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=".repeat(60));
    }


    public void displayAllCourses() {
        if (uniqueCourses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALL UNIQUE COURSES (Total: " + uniqueCourses.size() + ")");
        System.out.println("=".repeat(60));

        TreeSet<String> sortedCourses = new TreeSet<>(uniqueCourses);
        int count = 1;
        for (String course : sortedCourses) {
            System.out.printf("%2d. %s%n", count++, course);
        }
        System.out.println("=".repeat(60));
    }


    public void undoLastRemoval() {
        if (removedStudents.isEmpty()) {
            System.out.println("No removals to undo.");
            return;
        }

        Student student = removedStudents.pop();


        studentList.add(student);
        studentVector.add(student);
        studentLinkedList.add(student);
        studentHashMap.put(student.getStudentId(), student);
        studentMarks.put(student.getStudentId(), student.getMarks());
        sortedStudentMap.put(student.getStudentId(), student);
        uniqueCourses.add(student.getCourse());

        System.out.println("✓ Last removal undone successfully!");
    }


    public void displayVectorContents() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("VECTOR CONTENTS (Thread-Safe Storage)");
        System.out.println("=".repeat(120));

        synchronized(studentVector) {
            for (Student student : studentVector) {
                System.out.println(student);
            }
        }
        System.out.println("=".repeat(120));
    }


    public void displayHashtableContents() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("HASHTABLE CONTENTS (ID -> Marks Mapping)");
        System.out.println("=".repeat(60));

        for (Map.Entry<String, Double> entry : studentMarks.entrySet()) {
            System.out.printf("%-15s : %.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=".repeat(60));
    }
}


public class StudentManagementApp {
    public static void main(String[] args) {
        UniversityManagementSystem system = new UniversityManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStudentMenu(scanner, system);
                        break;
                    case 2:
                        system.displayAllStudents();
                        break;
                    case 3:
                        System.out.print("Enter Student ID to remove: ");
                        String removeId = scanner.nextLine();
                        system.removeStudentById(removeId);
                        break;
                    case 4:
                        System.out.print("Enter Student ID to search: ");
                        String searchId = scanner.nextLine();
                        system.searchStudentById(searchId);
                        break;
                    case 5:
                        system.sortStudentsByMarks();
                        break;
                    case 6:
                        system.convertHashMapToTreeMap();
                        break;
                    case 7:
                        system.countStudentsCourseWise();
                        break;
                    case 8:
                        system.displayAllCourses();
                        break;
                    case 9:
                        system.undoLastRemoval();
                        break;
                    case 10:
                        system.displayVectorContents();
                        break;
                    case 11:
                        system.displayHashtableContents();
                        break;
                    case 0:
                        System.out.println("\nThank you for using University Management System!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("✗ Error: Invalid input! Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("      UNIVERSITY STUDENT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Display All Students");
        System.out.println("3.  Remove Student by ID");
        System.out.println("4.  Search Student by ID");
        System.out.println("5.  Sort Students by Marks");
        System.out.println("6.  Convert HashMap to TreeMap (Display Sorted)");
        System.out.println("7.  Count Students Course-wise");
        System.out.println("8.  Display All Unique Courses");
        System.out.println("9.  Undo Last Removal (Stack Operation)");
        System.out.println("10. Display Vector Contents");
        System.out.println("11. Display Hashtable Contents");
        System.out.println("0.  Exit");
        System.out.println("=".repeat(60));
    }

    private static void addStudentMenu(Scanner scanner, UniversityManagementSystem system) {
        System.out.println("\n--- ADD NEW STUDENT ---");

        System.out.print("Enter Student ID (Format: STU####): ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Marks (0-100): ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        Student student = new Student(id, name, email, course, marks);
        system.addStudent(student);
    }
}