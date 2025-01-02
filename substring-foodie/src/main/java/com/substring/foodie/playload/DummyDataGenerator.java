package com.substring.foodie.playload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyDataGenerator {

    public static List<Student> generateDummyStudents() {
        List<Student> students = new ArrayList<>();

        // Sample engineering departments
        Department computerScience = new Department();
        computerScience.setDepartmentName("Computer Science");
        computerScience.setDepartmentCode("CS");

        Department mechanical = new Department();
        mechanical.setDepartmentName("Mechanical Engineering");
        mechanical.setDepartmentCode("ME");

        Department electronics = new Department();
        electronics.setDepartmentName("Electronics and Communication");
        electronics.setDepartmentCode("EC");

        // Sample engineering subjects
        List<Subject> engineeringSubjects = Arrays.asList(
                createSubject("Data Structures", "CS101"),
                createSubject("Thermodynamics", "ME101"),
                createSubject("Analog Circuits", "EC101"),
                createSubject("Operating Systems", "CS102"),
                createSubject("Fluid Mechanics", "ME102"),
                createSubject("Digital Communication", "EC102")
        );

        // List of Indian names
        List<String> indianNames = Arrays.asList(
                "Aarav", "Vivaan", "Aditya", "Ananya", "Diya", "Ishaan", "Meera", "Kavya", "Rohan", "Sanya"
        );

        // Generate 10 students
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName(indianNames.get(i));
            student.setAge(18 + (i % 5)); // Age varies between 18 and 22
            student.setSubjects(selectRandomSubjects(engineeringSubjects));

            // Assign departments cyclically
            if (i % 3 == 0) {
                student.setDepartment(computerScience);
            } else if (i % 3 == 1) {
                student.setDepartment(mechanical);
            } else {
                student.setDepartment(electronics);
            }

            students.add(student);
        }

        return students;
    }

    private static Subject createSubject(String title, String code) {
        Subject subject = new Subject();
        subject.setTitle(title);
        subject.setSubjectCode(code);
        return subject;
    }

    private static List<Subject> selectRandomSubjects(List<Subject> subjects) {
        List<Subject> selectedSubjects = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // Select 3 random subjects
            selectedSubjects.add(subjects.get((int) (Math.random() * subjects.size())));
        }
        return selectedSubjects;
    }

    public static void main(String[] args) {
        List<Student> students = generateDummyStudents();
        for (Student student : students) {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Department: " + student.getDepartment().getDepartmentName());
            System.out.println("Subjects: ");
            for (Subject subject : student.getSubjects()) {
                System.out.println("  - " + subject.getTitle() + " (" + subject.getSubjectCode() + ")");
            }
            System.out.println();
        }
    }
}
