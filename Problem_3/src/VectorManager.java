import java.util.Vector;

public class VectorManager implements studentOperations {
    private Vector<Student> students=new Vector<>();
    @Override
    public void addStudent(Student student) {
        for(Student s: students){
            if(s.getRollNo()==student.getRollNo()){
                throw new IllegalArgumentException("Student with roll number "+student.getRollNo()+ " already exists.");
            }
        }
        students.add(student);
    }

    @Override
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n⚠ No students found in Vector!\n");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            STUDENTS LIST (Vector)                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Roll No    Name                 Email                          Phone           CGPA     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");

        for (Student student : students) {
            System.out.println("║ " + student + " ║");
        }

        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Total Students: " + students.size() + "                                                                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════╝\n");
    }

    @Override
    public boolean removeStudent(int rollNo) {
        return students.removeIf(student -> student.getRollNo()==rollNo);
    }

    @Override
    public Student searchStudent(int rollNo) {
        for (Student student: students){
            if (student.getRollNo()==rollNo){
                return student;
            }
        }
        return null;
    }

    @Override
    public int getStudentCount() {
        return students.size();
    }
}
