import java.util.Map;
import java.util.TreeMap;

class TreeMapManager implements EmployeeOperations {
    private TreeMap<Integer, Employee> employees = new TreeMap<>();

    @Override
    public void addEmployee(Integer empId, Employee employee) {
        if (empId == null) {
            throw new IllegalArgumentException("TreeMap does not allow null keys!");
        }
        if (employees.containsKey(empId)) {
            throw new IllegalArgumentException("Employee with ID " + empId + " already exists!");
        }
        employees.put(empId, employee);
    }

    @Override
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\n⚠ No employees found in TreeMap!\n");
            return;
        }

        System.out.println("\n================================EMPLOYEES LIST(HashMap)==================================");
        System.out.println(" Emp ID    Name             Email                Phone        Department        Salary" );
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            Integer empId = entry.getKey();
            Employee emp = entry.getValue();
            System.out.printf("║ %-8d %s ║%n",
                    empId,
                    emp != null ? emp : "null                                                              ");
        }

        }


    @Override
    public Employee searchEmployee(Integer empId) {
        if (empId==null) {
            System.out.println("⚠ TreeMap does not support null keys!");
            return null;
        }
        return employees.get(empId);
    }

    @Override
    public boolean removeEmployee(Integer empId) {
        if (empId == null) {
            System.out.println("⚠ TreeMap does not support null keys!");
            return false;
        }
        return employees.remove(empId) != null;
    }

    @Override
    public int getEmployeeCount() {
        return employees.size();
    }

    @Override
    public void demonstrateNullSupport() {

        System.out.println("\n--- Testing NULL KEY ---");
        try {
            Employee testEmp = new Employee("Test User", "test@example.com", "9876543210", "Testing", 50000);
            employees.put(null, testEmp);
            System.out.println("✓ NULL KEY accepted");
        } catch (NullPointerException e) {
            System.out.println("✗ NULL KEY is NOT ALLOWED in TreeMap");
            System.out.println("  Exception: " + e.getClass().getSimpleName());
            System.out.println("  Reason: TreeMap needs to compare keys for sorting");
        }


        System.out.println("\n--- Testing NULL VALUE ---");
        try {
            employees.put(9999, null);
            System.out.println("✓ NULL VALUE is ALLOWED in TreeMap");
            System.out.println("  Added null value with key 9999 successfully!");


            System.out.println("  Value for key 9999: " + employees.get(9999));
            employees.remove(9999);
            System.out.println("  Test entry removed.");
        } catch (Exception e) {
            System.out.println("✗ NULL VALUE is NOT ALLOWED in TreeMap");
            System.out.println("  Exception: " + e.getClass().getSimpleName());
        }

        System.out.println("\n✓ Demonstration complete!");
        System.out.println("  TreeMap maintains sorted order but does NOT allow null keys.\n");
    }

    @Override
    public String getMapType() {
        return "TreeMap";
    }
}