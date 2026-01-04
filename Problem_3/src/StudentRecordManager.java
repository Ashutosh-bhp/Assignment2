import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRecordManager {
    private static Scanner scanner = new Scanner(System.in);
    private static studentOperations currentManager;
    private static String currentCollectionType = "";

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        STUDENT RECORD MANAGEMENT SYSTEM                    ║");
        System.out.println("║     Using ArrayList, Vector, and LinkedList                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        selectCollectionType();

        int choice;
        do {
            try {
                displayMainMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStudentRecord();
                        break;
                    case 2:
                        currentManager.displayAllStudents();
                        break;
                    case 3:
                        removeStudentRecord();
                        break;
                    case 4:
                        searchStudentRecord();
                        break;
                    case 5:
                        displayStatistics();
                        break;
                    case 6:
                        selectCollectionType();
                        break;
                    case 7:
                        System.out.println("\n✓ Thank you for using Student Record Management System!");
                        System.out.println("Exiting program...\n");
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please select 1-7.\n");
                }

                if (choice != 7 && choice >= 1 && choice <= 6) {
                    System.out.print("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("\n✗ Invalid input! Please enter a number.\n");
                scanner.nextLine(); // Clear invalid input
                choice = 0;
            } catch (Exception e) {
                System.out.println("\n✗ Error: " + e.getMessage() + "\n");
                choice = 0;
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void selectCollectionType() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          SELECT COLLECTION TYPE                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ArrayList (Fast random access)                         ║");
        System.out.println("║  2. Vector (Thread-safe ArrayList)                         ║");
        System.out.println("║  3. LinkedList (Fast insertion/deletion)                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        try {
            System.out.print("\nEnter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currentManager = new ArrayListManager();
                    currentCollectionType = "ArrayList";
                    System.out.println("\n✓ ArrayList selected for student management.\n");
                    break;
                case 2:
                    currentManager = new VectorManager();
                    currentCollectionType = "Vector";
                    System.out.println("\n✓ Vector selected for student management.\n");
                    break;
                case 3:
                    currentManager = new LinkedListManager();
                    currentCollectionType = "LinkedList";
                    System.out.println("\n✓ LinkedList selected for student management.\n");
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice! Defaulting to ArrayList.\n");
                    currentManager = new ArrayListManager();
                    currentCollectionType = "ArrayList";
            }
        } catch (Exception e) {
            System.out.println("\n⚠ Invalid input! Defaulting to ArrayList.\n");
            scanner.nextLine();
            currentManager = new ArrayListManager();
            currentCollectionType = "ArrayList";
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              MAIN MENU (" + String.format("%-27s", currentCollectionType) + ")        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Add Student Record                                     ║");
        System.out.println("║  2. Display All Students                                   ║");
        System.out.println("║  3. Remove Student by Roll Number                          ║");
        System.out.println("║  4. Search Student by Roll Number                          ║");
        System.out.println("║  5. Display Statistics                                     ║");
        System.out.println("║  6. Change Collection Type                                 ║");
        System.out.println("║  7. Exit                                                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    private static void addStudentRecord() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ADD STUDENT RECORD                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        try {
            System.out.print("\nEnter Roll Number: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter CGPA (0.0 - 10.0): ");
            double cgpa = scanner.nextDouble();
            scanner.nextLine();

            Student student = new Student(rollNo, name, email, phone, cgpa);
            currentManager.addStudent(student);

            System.out.println("\n✓ Student record added successfully!");
            System.out.println("Student Details:");
            student.displayDetails();

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input type! Please enter correct data types.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Error adding student: " + e.getMessage());
        }
    }

    private static void removeStudentRecord() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           REMOVE STUDENT RECORD                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        try {
            System.out.print("\nEnter Roll Number to remove: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine();

            boolean removed = currentManager.removeStudent(rollNo);

            if (removed) {
                System.out.println("\n✓ Student with Roll Number " + rollNo + " removed successfully!");
            } else {
                System.out.println("\n✗ Student with Roll Number " + rollNo + " not found!");
            }

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input! Please enter a valid roll number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\n✗ Error removing student: " + e.getMessage());
        }
    }

    private static void searchStudentRecord() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           SEARCH STUDENT RECORD                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        try {
            System.out.print("\nEnter Roll Number to search: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine();

            Student student = currentManager.searchStudent(rollNo);

            if (student != null) {
                System.out.println("\n✓ Student found!");
                student.displayDetails();
            } else {
                System.out.println("\n✗ Student with Roll Number " + rollNo + " not found!");
            }

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input! Please enter a valid roll number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\n✗ Error searching student: " + e.getMessage());
        }
    }

    private static void displayStatistics() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              SYSTEM STATISTICS                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Collection Type    : %-37s ║%n", currentCollectionType);
        System.out.printf("║ Total Students     : %-37d ║%n", currentManager.getStudentCount());
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}