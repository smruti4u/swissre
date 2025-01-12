package org.swissre.service;

import org.swissre.entity.Employee;
import org.swissre.entity.ManagerEarning;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface for OrgManagerService
 */
public interface OrgManagerService {

    /**
     * Initializes application with managerMap and employeeMap
     * @throws FileNotFoundException when CSV file path does not exist or Invalid
     */
    void bootStrapApplication() throws FileNotFoundException;

    /**
     * @param earning ManagerEarning LESS or MORE
     * @return List Of Managers
     */
    List<Employee> getManagerEarning(ManagerEarning earning);

    /**
     * get Employees with more than 4 managers from CEO
     * 4 has been hard coded as constants and can be got from config as well
     * @return List Of Employees
     */
    List<Employee> getEmployeesWithLongReporting();

    /**
     * prints all employee present in employee map
     */
    void printAllEmployee();
}
