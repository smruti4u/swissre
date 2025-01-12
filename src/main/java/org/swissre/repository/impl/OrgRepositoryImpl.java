package org.swissre.repository;

import org.swissre.entity.Employee;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * OrgRepository loads
 * managerMap and employeeMap and gives average subordinate salary
 */
public class OrgRepository {
    public static Map<String, TreeSet<Employee>> managerMap = new HashMap<>();
    public static Map<String, Employee> employeeMap = new HashMap<>();

    private final CSVRepository csvRepository;

    public OrgRepository(CSVRepository csvRepository) {
        this.csvRepository = csvRepository;
    }

    /**
     * loads employeeMap and managerMap
     * @throws FileNotFoundException when file not found at specified path
     */
    public void loadData() throws FileNotFoundException {
        Set<Employee> employeeList = this.csvRepository.readFile();
        for (Employee e : employeeList) {

            populateEmployeeMap(e);
            populateManagerMap(e);
        }
    }

    /**
     * populates Manager Map with sub oridinates for manager search
     * @param employee Employee object
     */
    private static void populateManagerMap(Employee employee) {
        if (managerMap.containsKey(employee.getManagerId())) {
            managerMap.get(employee.getManagerId()).add(employee);
        } else {
            TreeSet<Employee> newEmployee = new TreeSet<>();
            newEmployee.add(employee);
            if (employee.getManagerId() == null) {
                employee.setCEO(true);
            }
            managerMap.put(employee.getManagerId(), newEmployee);
        }
    }

    /**
     * populates Employee Map for employee search
     * @param employee Employee object
     */
    private static void populateEmployeeMap(Employee employee) {
        // Populating the employee map
        employeeMap.put(employee.getId(), employee);
    }

    /**
     * Calculate the average salary of all subordinates reporting to a specific manager
     * @param managerId managerId for which average salary of subordinate will be calculated
     * @return average salary
     */
    public BigDecimal getAverageSalary(String managerId) {
        BigDecimal sum = BigDecimal.valueOf(0);
        Set<Employee> subordinates =  OrgRepository.managerMap.get(managerId);
        for(Employee subordinate : subordinates)
        {
            sum = sum.add(subordinate.getSalary());
        }

        return sum.divide(BigDecimal.valueOf(subordinates.size()), 2, RoundingMode.HALF_DOWN);


    }
}
