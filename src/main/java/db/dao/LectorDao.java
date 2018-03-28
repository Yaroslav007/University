package db.dao;

import db.entity.Department;
import db.entity.Lector;
import db.entity.Post;

import javax.persistence.EntityManager;
import java.util.List;

public class LectorDao {

    public List<Lector> findWithName(String name) {
        EntityManager entityManager = GenericDao.getEntityManager();
        List <Lector> lectors =entityManager.createQuery(
            "SELECT l FROM Lector l WHERE l.fullName LIKE :custName")
                .setParameter("custName", "%"+name+"%")
                .getResultList();
        return lectors;


//        List<Department> departments = entityManager.createQuery(
//                "SELECT d FROM Department d WHERE d.name LIKE :custName")
//                .setParameter("custName", "%" + name + "%")
//                .getResultList();
    }

    public long count(Department department, Post post) {
        EntityManager entityManager = GenericDao.getEntityManager();
        long number = (long) entityManager.createQuery(
                "SELECT COUNT(l.post) FROM Lector l WHERE l.department=:department AND l.post =:post")
                .setParameter("department", department).setParameter("post",  post).getSingleResult();
        return number;
    }

    public double averageSalary(Department department){
        EntityManager entityManager = GenericDao.getEntityManager();
        double number = (double) entityManager.createQuery(
                "SELECT AVG(l.salary) FROM Lector l WHERE l.department=:department")
                .setParameter("department", department).getSingleResult();
        System.out.println(number);
        return number;
    }

    public long countEmployees(Department department) {
        EntityManager entityManager = GenericDao.getEntityManager();
        long number = (long) entityManager.createQuery(
                "SELECT COUNT(l.id) FROM Lector l WHERE l.department=:department")
                .setParameter("department", department).getSingleResult();
        return number;
    }


}
