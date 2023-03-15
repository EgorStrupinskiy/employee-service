//package com.strupinski.employeeservice.dao;
//
//
//import com.strupinski.employeeservice.entity.Employee;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//public class EmployeeDAOImpl implements EmployeeDAO {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Employee> getAllEmployees() {
//        Query query = entityManager.createQuery("from Employee", Employee.class);
//        return (query.getResultList());
//    }
//
//    @Override
//    public void saveEmployee(Employee employee) {
//        Employee newEmployee = entityManager.merge(employee);
//        employee.setId(newEmployee.getId());
//    }
//
//    @Override
//    public Employee getEmployee(int id) {
//        Employee employee = entityManager.find(Employee.class, id);
//        return employee;
//    }
//
//    @Override
//    public void deleteEmployee(int id) {
//        Query query = entityManager.createQuery("delete from Employee " +
//                "where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
//    }
//}
