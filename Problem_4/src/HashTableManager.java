import java.util.Hashtable;
import java.util.Map;

public class HashTableManager implements EmployeeOperations{
    Hashtable<Integer, Employee> employees=new Hashtable<>();

    @Override
    public void addEmployee(Integer empID, Employee employee) {
        if(empID==null){
            throw new IllegalArgumentException("HashTable doest not allow null keys!");
        }
        if(employee==null){
            throw new IllegalArgumentException("Hashtable does not allow null values");
        }
        employees.put(empID,employee);
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



        System.out.println("\n==== Testing NULL KEY ====");
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
        return "HashTable";
    }
}
