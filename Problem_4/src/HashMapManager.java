import java.util.HashMap;
import java.util.Map;

public class HashMapManager implements EmployeeOperations{
    private HashMap<Integer, Employee> employees=new HashMap<>();
    @Override
    public void addEmployee(Integer empId, Employee employee) {
        if(employees.containsKey(empId)){
            throw new IllegalArgumentException("Employee with ID "+empId+" already exists.");
        }
        employees.put(empId, employee);
    }

    @Override
    public void displayAllEmployees() {
        if(employees.isEmpty()){
            System.out.println("\n No employees found in the HashMap.");
        }
        System.out.println("\n================================EMPLOYEES LIST(HashMap)==================================");
        System.out.println(" Emp ID    Name             Email                Phone        Department        Salary" );
        for (Map.Entry<Integer,Employee> entry: employees.entrySet()){
            Integer empId= entry.getKey();
            Employee emp= entry.getValue();
            System.out.printf(" %-8s %s %n", empId!=null? empId:"null", emp!=null? emp:"null");
        }

    }

    @Override
    public boolean removeEmployee(Integer employeeId) {
        return employees.remove(employeeId)!=null;
    }

    @Override
    public Employee searchEmployee(Integer employeeId) {
        return employees.get(employeeId);
    }

    @Override
    public int getEmployeeCount() {
        return employees.size();
    }

    @Override
    public void demonstrateNullSupport() {

        try {
            Employee testEmp1 = new Employee("Test User", "test@example.com", "9876543210", "Testing", 50000);
            employees.put(null, testEmp1);
            System.out.println("\n✓ NULL KEY is ALLOWED in HashMap");
            System.out.println("  Added employee with null key successfully!");


            employees.put(9999, null);
            System.out.println("\n✓ NULL VALUE is ALLOWED in HashMap");
            System.out.println("  Added null value with key 9999 successfully!");


            System.out.println("\n--- Verifying Null Entries ---");
            System.out.println("Employee with null key: " + employees.get(null));
            System.out.println("Value for key 9999: " + employees.get(9999));

            employees.remove(null);
            employees.remove(9999);
            System.out.println("\n✓ Test entries removed. HashMap restored to original state.\n");

        } catch (Exception e) {
            System.out.println("\n✗ Error during null test: " + e.getMessage() + "\n");
        }
    }

    @Override
    public String getMapType() {
        return "HashMap";
    }
}
