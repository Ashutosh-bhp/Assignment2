public interface EmployeeOperations {
    void addEmployee(Integer empID, Employee employee);
    void displayAllEmployees();
    boolean removeEmployee(Integer employeeId);
    Employee searchEmployee(Integer employeeId);
    int getEmployeeCount();
    void demonstrateNullSupport();
    String getMapType();
}
