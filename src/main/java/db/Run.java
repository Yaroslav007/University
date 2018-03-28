package db;

import db.dao.DepartmentDao;
import db.dao.GenericDao;
import db.dao.LectorDao;
import db.entity.Department;
import db.entity.Lector;
import db.entity.Post;
import db.utills.Scan;

import java.util.List;

public class Run {

    static DepartmentDao departmentDao = new DepartmentDao();
    static LectorDao lectorDao = new LectorDao();

    public static void main(String[] args) {
        GenericDao.openFactory();


        while (true) {
            int shoice = Scan.scannerInt("      Please, make your choise:" + "\n" +
                    "1 - Head of department" + "\n" +
                    "2 - Show department statistic" + "\n" +
                    "3 - Show the average salary for department" + "\n" +
                    "4 - Show count of employee for department" + "\n" +
                    "5 - Global search of lectors" + "\n" +
                    "6 - Add Lector" + "\n" +
                    "7 - Add Department");
            switch (shoice) {
                case 1:
                    departmentLead();
                    break;
                case 2:
                    statistic();
                    break;
                case 3:
                    everageSalary();
                    break;
                case 4:
                    employeeCount();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    addLector();
                    break;
                case 7:
                    addDepartment();
                    break;
                default:
                    System.out.println("close program");
                    GenericDao.closeFactory();
                    return;
            }
        }
    }

    private static void search() {
        String name = Scan.scannerString("Write the full name (or name, surname only):");
        List<Lector> lectors = lectorDao.findWithName(name);
        for (Lector l:lectors) {
            System.out.println(l.getFullName());
        }
    }

    private static void employeeCount() {
        Department department = findDepartment();
        long count = lectorDao.countEmployees(department);
        System.out.println("Number of workers in the" + department.getName()+ " department is " + count);
    }

    private static Department findDepartment(){
        String name = Scan.scannerString("Write department name: ");
        List<Department> departments = departmentDao.findWithName(name);
        Department department;
        if (departments.size()>1){
            System.out.println(departments);
            int depId = Scan.scannerInt("Choose department id: ");
            department = departments.get(depId);
        }else {
            department = departments.get(0);
        }
        return department;
    }

    private static void everageSalary() {
        Department department = findDepartment();
        System.out.println("1111"+department);
        try {
            double avgSalary = lectorDao.averageSalary(department);
            System.out.println("The average salary of " + department.getName() + " is " + avgSalary);
        }catch (NullPointerException e){
            System.out.println("There are no lecturers in this department");
        }
    }

    private static void statistic() {
        Department department = findDepartment();
        long assistant =lectorDao.count(department,Post.ASISTANT);
        long associateProfessors = lectorDao.count(department, Post.ASSOCIATE_PROFESORS);
        long professors= lectorDao.count(department,Post.PROFESSORS);
        System.out.println("assistans - "+ assistant + "\n" +
                "associate professors - " +associateProfessors + "\n" +
                "professors - " + professors);
    }

    private static void departmentLead() {
        String name = Scan.scannerString("Write the name of department: ");
        List<Department> d = departmentDao.findLead(name);
        for (Department dep:d) {
            System.out.println("Head of "+ name+ " departmemt is " + dep.getDepartmentLead());
        }
    }

    private static void addDepartment() {
        String name = Scan.scannerString("Write the name of department");
        String depLead= Scan.scannerString("Write the full name of department lead");
        Department department = new Department(name,depLead);
        GenericDao.save(department);
        System.out.println("Department saved");

    }

    private static void addLector() {
        String name = Scan.scannerString("Write the full name of lector: ");
        int salary = Scan.scannerInt("Write the salary of lector: ");
        Post post = null;
        int choise = Scan.scannerInt("choose the post of lector: 1-assistan, 2-associate professors, 3-professors");
        if (choise == 1) {
            post = Post.ASISTANT;
        } else if (choise == 2) {
            post = Post.ASSOCIATE_PROFESORS;
        } else if (choise == 3) {
            post = Post.PROFESSORS;
        }

        if (post == null) {
            System.out.println("Incorrectly date");
        } else {
            Department department = findDepartment();
            Lector lector = new Lector(name, salary,post,department);
            GenericDao.save(lector);
            System.out.println("Lector saved.");
        }
    }
}
