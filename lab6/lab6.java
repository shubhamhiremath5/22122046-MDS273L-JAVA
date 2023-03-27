import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class lab6 {
    private static final int MAX_STUDENTS = 100;
    private static final Student[] students = new Student[MAX_STUDENTS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. Add a student");
            System.out.println("2. Search for a student");
            System.out.println("3. Update the details of a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save student details to file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    searchStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveStudentDetailsToFile(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (choice != 6);
    }

    private static void addStudent(Scanner scanner) {
        int regNo;
        String name, email, phone, cls, department;
        System.out.println("Enter the details of the student:");
        System.out.print("Reg No.: ");
        regNo = Integer.parseInt(scanner.nextLine());
        scanner.nextLine(); // consume the newline character
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Phone: ");
        phone = scanner.nextLine();
        System.out.print("Class: ");
        cls = scanner.nextLine();
        System.out.print("Department: ");
        department = scanner.nextLine();
        Student student = new Student(regNo, name, email, phone, cls, department);
        for (int i = 0; i < MAX_STUDENTS; i++) {
            if (students[i] == null) {
                students[i] = student;
                System.out.println("Student added successfully");
                break;
            }
            if (i == MAX_STUDENTS - 1) {
                System.out.println("Maximum number of students reached, cannot add more students");
            }
        }
    }

    private static void searchStudent(Scanner scanner) {
        System.out.println("Enter the search term (name or register number):");
        String searchTerm = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student != null) {
                if (student.getName().equalsIgnoreCase(searchTerm) || String.valueOf(student.getRegNo()).equals(searchTerm)) {
                    student.printDetails();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Student not found");
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("Enter the search term (name or register number):");
        String searchTerm = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student != null) {
                if (student.getName().equalsIgnoreCase(searchTerm) || String.valueOf(student.getRegNo()).equals(searchTerm)) {
                    System.out.println("Enter the new details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Class: ");
                    String cls = scanner.nextLine();
                    System.out.print("Department: ");
                    String department = scanner.nextLine();
                    student.setName(name);
                    student.setEmail(email);
                    student.setPhone(phone);
                    student.setCls(cls);
                    student.setDepartment(department);
                    System.out.println("Student details updated successfully");
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Student not found");
        }
    }

    private static void displayAllStudents() {
        for (Student student : students) {
            if (student != null) {
                student.printDetails();
            }
        }
    }

    private static void saveStudentDetailsToFile(Scanner scanner) {
        System.out.println("Enter the name of the student to save the details to file:");
        String studentName = scanner.nextLine();
        for (Student student : students) {
            if (student != null) {
                if (student.getName().equalsIgnoreCase(studentName)) {
                    String fileName = student.getName() + ".txt";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        writer.write(student.toString());
                        System.out.println("Student details saved to file successfully");
                    } catch (IOException e) {
                        System.out.println("Error occurred while saving student details to file: " + e.getMessage());
                    }
                    return;
                }
            }
        }
        System.out.println("Student not found");
    }
}

class Student {
    private int regNo;
    private String name;
    private String email;
    private String phone;
    private String cls;
    private String department;

    public Student(int regNo, String name, String email, String phone, String cls, String department) {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cls = cls;
        this.department = department;
    }

    public void printDetails() {
        System.out.println("Reg No.: " + regNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Class: " + cls);
        System.out.println("Department: " + department);
    }

    public String toString() {
        return regNo + "," + name + "," + email + "," + phone + "," + cls + "," + department;
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
