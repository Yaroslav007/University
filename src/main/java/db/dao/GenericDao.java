package db.dao;


import db.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


public class GenericDao {
    protected static EntityManagerFactory factory;

    public static void openFactory(){
        Map<String, String> propertyes = new HashMap<>();
        String username = System.getenv("username");
        String password = System.getenv("password");
        propertyes.put("hibernate.connection.username", username);
        propertyes.put("hibernate.connection.password", password);
       factory = Persistence.createEntityManagerFactory("x", propertyes);
    }

    public static  void closeFactory(){
       factory.close();
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }


    public static void save(GenericEntity entity){
        EntityManager entityManager = GenericDao.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(entity);
        transaction.commit();
        entityManager.close();
    }

    public static void remove(GenericEntity entity){
        EntityManager entityManager = GenericDao.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));

        transaction.commit();
        entityManager.close();
    }

    public static void update(GenericEntity entity){
        EntityManager entityManager = GenericDao.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        entityManager.merge(entity);

        transaction.commit();
        entityManager.close();
    }

}