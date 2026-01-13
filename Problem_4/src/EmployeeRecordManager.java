import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeRecordManager {
    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeOperations currentManager;

    public static void main(String[] args) {

        System.out.println("        EMPLOYEE RECORD MANAGEMENT SYSTEM                   ");
        System.out.println("     Using HashMap, Hashtable, and TreeMap                  ");


        selectMapType();

        int choice;
        do {
            try {
                displayMainMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addEmployeeRecord();
                        break;
                    case 2:
                        currentManager.displayAllEmployees();
                        break;
                    case 3:
                        searchEmployeeRecord();
                        break;
                    case 4:
                        removeEmployeeRecord();
                        break;
                    case 5:
                        currentManager.demonstrateNullSupport();
                        break;
                    case 6:
                        displayStatistics();
                        break;
                    case 7:
                        selectMapType();
                        break;
                    case 8:
                        System.out.println("\n✓ Thank you for using Employee Record Management System!");
                        System.out.println("Exiting program...\n");
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please select 1-8.\n");
                }

                if (choice != 8 && choice >= 1 && choice <= 7) {
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

        } while (choice != 8);

        scanner.close();
    }

    private static void selectMapType() {

        System.out.println("===================== SELECT MAP TYPE==================");
        System.out.println("1. HashMap (Fast, allows null, not sorted)                ");
        System.out.println("2. Hashtable (Thread-safe, no null)                       ");
        System.out.println("3. TreeMap (Sorted by keys, no null keys)                 ");

        try {
            System.out.print("\nEnter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currentManager = new HashMapManager();
                    System.out.println("\n✓ HashMap selected for employee management.");
                    System.out.println("  Features: Fast access, allows null key & values, unordered\n");
                    break;
                case 2:
                    currentManager = new HashTableManager();
                    System.out.println("\n✓ Hashtable selected for employee management.");
                    System.out.println("  Features: Thread-safe, no null keys/values, unordered\n");
                    break;
                case 3:
                    currentManager = new TreeMapManager();
                    System.out.println("\n✓ TreeMap selected for employee management.");
                    System.out.println("  Features: Sorted by keys, no null keys, allows null values\n");
                    break;
                default:
                    System.out.println("\n⚠ Invalid choice! Defaulting to HashMap.\n");
                    currentManager = new HashMapManager();
            }
        } catch (Exception e) {
            System.out.println("\n⚠ Invalid input! Defaulting to HashMap.\n");
            scanner.nextLine();
            currentManager = new HashMapManager();
        }
    }

    private static void displayMainMenu() {

        System.out.println("\n           MAIN MENU (" + String.format("%-29s", currentManager.getMapType()) + ")        ");
        System.out.println("  1. Add Employee Record                                    ");
        System.out.println("  2. Display All Employees                                  ");
        System.out.println("  3. Search Employee by ID                                  ");
        System.out.println("  4. Remove Employee by ID                                  ");
        System.out.println("  5. Demonstrate Null Key/Value Support                     ");
        System.out.println("  6. Display Statistics                                     ");
        System.out.println("  7. Change Map Type                                        ");
        System.out.println("  8. Exit                                                   ");
    }

    private static void addEmployeeRecord() {

        try {
            System.out.print("\nEnter Employee ID: ");
            Integer empId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            Employee employee = new Employee(name, email, phone, department, salary);
            currentManager.addEmployee(empId, employee);

            System.out.println("\n✓ Employee record added successfully!");
            System.out.println("Employee Details:");
            employee.displayDetails();

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input type! Please enter correct data types.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Error adding employee: " + e.getMessage());
        }
    }

    private static void searchEmployeeRecord() {

        try {
            System.out.print("\nEnter Employee ID to search: ");
            Integer empId = scanner.nextInt();
            scanner.nextLine();

            Employee employee = currentManager.searchEmployee(empId);

            if (employee != null) {
                System.out.println("\n✓ Employee found!");
                employee.displayDetails();
            } else {
                System.out.println("\n✗ Employee with ID " + empId + " not found!");
            }

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input! Please enter a valid employee ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\n✗ Error searching employee: " + e.getMessage());
        }
    }

    private static void removeEmployeeRecord() {

        try {
            System.out.print("\nEnter Employee ID to remove: ");
            Integer empId = scanner.nextInt();
            scanner.nextLine();

            boolean removed = currentManager.removeEmployee(empId);

            if (removed) {
                System.out.println("\n✓ Employee with ID " + empId + " removed successfully!");
            } else {
                System.out.println("\n✗ Employee with ID " + empId + " not found!");
            }

        } catch (InputMismatchException e) {
            System.out.println("\n✗ Invalid input! Please enter a valid employee ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\n✗ Error removing employee: " + e.getMessage());
        }
    }

    private static void displayStatistics() {

        System.out.println("              SYSTEM STATISTICS                             ");
        System.out.printf(" Map Type           : %-37s %n", currentManager.getMapType());
        System.out.printf(" Total Employees    : %-37d %n", currentManager.getEmployeeCount());

        // Display map-specific characteristics
        String mapType = currentManager.getMapType();
        System.out.println(" Characteristics:                                           ");

        if (mapType.equals("HashMap")) {
            System.out.println("  • Fast access (O(1) average)                              ");
            System.out.println("  • Allows null keys and null values                        ");
            System.out.println("  • Not thread-safe                                         ");
            System.out.println("  • Unordered                                               ");
        } else if (mapType.equals("Hashtable")) {
            System.out.println("  • Thread-safe (synchronized)                              ");
            System.out.println("  • Does NOT allow null keys or values                      ");
            System.out.println("  • Slower than HashMap due to synchronization              ");
            System.out.println("  • Unordered (legacy class)                                ");
        } else if (mapType.equals("TreeMap")) {
            System.out.println("  • Sorted by keys (natural ordering)                       ");
            System.out.println("  • Does NOT allow null keys                                ");
            System.out.println("  • Allows null values                                      ");
            System.out.println("  • Slower access (O(log n))                                ");
        }


    }
}