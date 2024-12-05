package com.hibernateproject.Hibernateconfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

import com.hibernateproject.Hibernateconfig.entity.Student;

public class App {
    public static void main(String[] args) {
        // Create Scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magician");
        EntityManager em = emf.createEntityManager();

        while (true) {
            System.out.println("Select an operation:");
            System.out.println("1. Create a new student");
            System.out.println("2. Read a student by ID");
            System.out.println("3. Update a student's email");
            System.out.println("4. Delete a student by ID");
            System.out.println("5. Show all students");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // CREATE Operation: Ask user for student details and create a new student
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Student Email: ");
                        String email = scanner.nextLine();

                        em.getTransaction().begin();
                        Student newStudent = new Student(name, email);
                        em.persist(newStudent);  // Save student to DB
                        em.getTransaction().commit();
                        System.out.println("Student Created: " + newStudent.getId());
                        break;

                    case 2:
                        // READ Operation: Fetch student by ID from DB
                        System.out.print("Enter Student ID to read: ");
                        int idToRead = scanner.nextInt();
                        Student foundStudent = em.find(Student.class, idToRead);

                        if (foundStudent != null) {
                            System.out.println("Found Student: " + foundStudent.getName() + ", " + foundStudent.getEmail());
                        } else {
                            System.out.println("Student with ID " + idToRead + " not found.");
                        }
                        break;

                    case 3:
                        // UPDATE Operation: Ask user to enter ID and new email to update
                        System.out.print("Enter Student ID to update: ");
                        int idToUpdate = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        Student studentToUpdate = em.find(Student.class, idToUpdate);

                        if (studentToUpdate != null) {
                            System.out.print("Enter new email for the student: ");
                            String updatedEmail = scanner.nextLine();

                            em.getTransaction().begin();
                            studentToUpdate.setEmail(updatedEmail);  // Set new email
                            em.merge(studentToUpdate);  // Merge (update) student entity
                            em.getTransaction().commit();
                            System.out.println("Student Updated: " + studentToUpdate.getEmail());
                        } else {
                            System.out.println("Student with ID " + idToUpdate + " not found.");
                        }
                        break;

                    case 4:
                        // DELETE Operation: Ask user to enter ID to delete a student
                        System.out.print("Enter Student ID to delete: ");
                        int idToDelete = scanner.nextInt();
                        Student studentToDelete = em.find(Student.class, idToDelete);

                        if (studentToDelete != null) {
                            em.getTransaction().begin();
                            em.remove(studentToDelete);  // Remove the student from DB
                            em.getTransaction().commit();
                            System.out.println("Student Deleted: " + studentToDelete.getId());
                        } else {
                            System.out.println("Student with ID " + idToDelete + " not found.");
                        }
                        break;

                    case 5:
                        // SHOW all students in the database
                        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
                        if (students.isEmpty()) {
                            System.out.println("No students found in the database.");
                        } else {
                            System.out.println("All Students:");
                            for (Student student : students) {
                                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Email: " + student.getEmail());
                            }
                        }
                        break;

                    case 6:
                        // Exit the program
                        System.out.println("Exiting program...");
                        scanner.close();
                        em.close();
                        emf.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (Exception e) {
                em.getTransaction().rollback();  // Rollback transaction if there's an error
                e.printStackTrace();
            }
        }
    }
}
