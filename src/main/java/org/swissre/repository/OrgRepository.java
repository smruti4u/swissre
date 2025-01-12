package org.swissre.repository;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

/**
 * OrgRepository
 */
public interface OrgRepository {
    /**
     * loads employeeMap and managerMap
     * @throws FileNotFoundException when file not found at specified path
     */
    void loadData() throws FileNotFoundException;

    /**
     * Calculate the average salary of all subordinates reporting to a specific manager
     * @param managerId managerId for which average salary of subordinate will be calculated
     * @return average salary
     */
    BigDecimal getAverageSalary(String managerId);
}
