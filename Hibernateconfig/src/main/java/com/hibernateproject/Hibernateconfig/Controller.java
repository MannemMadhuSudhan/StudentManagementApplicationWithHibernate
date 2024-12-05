//package com.hibernateproject.Hibernateconfig;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//import com.hibernateproject.Hibernateconfig.entity.Department;
//
//public class Controller {
//public static void main(String[] args) {
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("magician");
//	EntityManager em = emf.createEntityManager();
//	EntityTransaction et = em.getTransaction();
//    
//	Department dept = new Department();
////	dept.setId(1);
////	dept.setName("madhu");
////	dept.setDepno("alfa");
//	
//	em.persist(dept);
//	et.commit();
//	
//	em.close();
//	
//}
//}
//
////package com.hibernateproject.Hibernateconfig;
////
////import javax.persistence.EntityManager;
////import javax.persistence.EntityManagerFactory;
////import javax.persistence.EntityTransaction;
////import javax.persistence.Persistence;
////
////import com.hibernateproject.Hibernateconfig.entity.Department;
////
////public class Controller {
////    public static void main(String[] args) {
////        // Create EntityManagerFactory
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magician");
////        EntityManager em = emf.createEntityManager();
////        EntityTransaction et = em.getTransaction();
////
////        try {
////            // Begin transaction
////            et.begin();
////
////            // Create a new Department entity
////            Department dept = new Department();
////            dept.setId(1);
////            dept.setName("madhu");
////            dept.setDepno("alfa");
////
////            // Persist the entity
////            em.persist(dept);
////
////            // Commit the transaction
////            et.commit();
////
////            System.out.println("Department persisted successfully!");
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            if (et.isActive()) {
////                et.rollback(); // Rollback the transaction in case of an error
////            }
////        } finally {
////            em.close(); // Close the EntityManager
////            emf.close(); // Close the EntityManagerFactory
////        }
////    }
////}
