import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    public boolean enrollStudent() {
        if (getAvailableSlots() > 0) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    public void displayCourse() {
        System.out.printf("Code: %s, Title: %s, Description: %s, Capacity: %d, Available Slots: %d%n",
                code, title, description, capacity, getAvailableSlots());
    }
}


class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            return true;
        }
        return false;
    }
}


public class StudentCourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));

        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.print((i + 1) + ". ");
            courses.get(i).displayCourse();
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        listCourses();
        System.out.print("Enter course number to register (1-" + courses.size() + "): ");
        int courseIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (courseIndex >= 0 && courseIndex < courses.size()) {
            Course course = courses.get(courseIndex);
            if (student.registerCourse(course)) {
                System.out.println("Successfully registered for " + course.getTitle());
            } else {
                System.out.println("Failed to register. Course may be full.");
            }
        } else {
            System.out.println("Invalid course number.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
            return;
        }

        System.out.println("Your registered courses:");
        for (int i = 0; i < registeredCourses.size(); i++) {
            Course course = registeredCourses.get(i);
            System.out.printf("%d. %s%n", i + 1, course.getTitle());
        }

        System.out.print("Enter the number of the course to drop (1-" + registeredCourses.size() + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (index >= 0 && index < registeredCourses.size()) {
            Course course = registeredCourses.get(index);
            if (student.dropCourse(course)) {
                System.out.println("Successfully dropped " + course.getTitle());
            } else {
                System.out.println("Failed to drop the course.");
            }
        } else {
            System.out.println("Invalid course number.");
        }
    }

    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
