import java.util.Scanner;

public class StudentGrading {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();

        
        int[] marks = new int[numSubjects];

        
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

    
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        
        double averagePercentage = (double) totalMarks / numSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }


        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f\n", averagePercentage);
        System.out.println("Grade: " + grade);

    }
}
