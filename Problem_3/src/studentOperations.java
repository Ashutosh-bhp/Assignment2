public interface studentOperations {
    void addStudent(Student student);
    void displayAllStudents();
    boolean removeStudent(int rollNo);
    Student searchStudent(int rollNo);
    int getStudentCount();

}
