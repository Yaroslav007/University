package db.dao;


import db.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DepartmentDao {

    public List<Department> findLead(String name) {
        EntityManager entityManager = GenericDao.getEntityManager();
        List <Department> departments  = entityManager.createQuery(
                "SELECT d FROM Department d WHERE d.name LIKE :custName")
                .setParameter("custName", "%"+name+"%").getResultList();
        return departments;
    }
    public List<Department> findWithName(String name) {
        EntityManager entityManager = GenericDao.getEntityManager();
        List<Department> departments = entityManager.createQuery(
                "SELECT d FROM Department d WHERE d.name LIKE :custName")
                .setParameter("custName", "%" + name + "%")
                .getResultList();
        return departments;
    }
}
