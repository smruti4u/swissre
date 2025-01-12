package org.swissre.service;

import org.swissre.entity.Employee;
import org.swissre.entity.ManagerEarning;
import org.swissre.repository.OrgRepository;
import org.swissre.utils.Constants;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrgManagerServiceImpl implements OrgManagerService {

    /**
     * OrgRepository dependency
     */
    private final OrgRepository orgRepository;

    /**
     * OrgManagerServiceImpl constructor Injection
     * @param orgRepository dependency
     */
    public OrgManagerServiceImpl(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    /**
     * Initializes application with managerMap and employeeMap
     * @throws FileNotFoundException when CSV file path does not exist or Invalid
     */
    @Override
    public void bootStrapApplication() throws FileNotFoundException {
        orgRepository.loadData();
    }

    /**
     * @param earning ManagerEarning LESS or MORE
     * @return List Of Managers
     */
    @Override
    public List<Employee> getManagerEarning(ManagerEarning earning) {
        List<Employee> managers = new ArrayList<>();
        System.out.println("------------------------------------");
        System.out.println("Manager Salary Detail");
        OrgRepository.managerMap.forEach((managerId, employees) -> {

            // Handling CEO Use Case where Manager ID is null
            if (managerId != null) {
                // Calculate Average Salary Of All Employees reporting to manager
                BigDecimal averageSalary = this.orgRepository.getAverageSalary(managerId);

                BigDecimal averageLowerSalary = averageSalary
                        .add(averageSalary
                                .multiply(BigDecimal.valueOf(Constants.TWENTY_PERCENT)));


                BigDecimal averageHigherSalary = averageSalary
                        .add(averageSalary
                                .multiply(BigDecimal.valueOf(Constants.FIFTY_PERCENT)));


                // Get The Manager details from Manager Map
                Employee manager = OrgRepository.employeeMap.get(managerId);
                System.out.println("Average higher Salary for Manager with Id " + manager.getManagerId() + " " + manager.getFirstName() +  " " + manager.getSalary()  + ": averageHigherSalary: " + averageHigherSalary + "averageLowerSalary " + averageLowerSalary + " averageSalary :  "+ averageSalary);

                BigDecimal currentManagerSalary = manager.getSalary();

                if (earning == ManagerEarning.LESS)
                    if (currentManagerSalary.compareTo(averageLowerSalary) < 0) {
                    BigDecimal changePercentage = ((averageLowerSalary.subtract(currentManagerSalary)).divide(averageLowerSalary, 2, RoundingMode.UP)).multiply(BigDecimal.valueOf(100)) ;
                    manager.setEarningLessPercentage(changePercentage);
                    managers.add(manager);
                }

                if (earning == ManagerEarning.MORE)
                    if (currentManagerSalary.compareTo(averageHigherSalary) > 0) {
                        BigDecimal changePercentage = ((currentManagerSalary.subtract(averageHigherSalary)).divide(currentManagerSalary, 2, RoundingMode.UP) ).multiply(BigDecimal.valueOf(100));
                    manager.setEarningMorePercentage(changePercentage);
                    managers.add(manager);
                }

            }
        });
        return managers;
    }

    /**
     * get Employees with more than 4 managers from CEO
     * 4 has been hard coded as constants and can be got from config as well
     * @return List Of Employees
     */
    @Override
    public List<Employee> getEmployeesWithLongReporting() {
        Queue<Employee> queue = new LinkedList<>();
        List<Employee> result = new ArrayList<>();
        int employeeLevel = -1;
        queue.add(OrgRepository.managerMap.get(null).first());
        while (!queue.isEmpty()) {
            int count = queue.size();
            employeeLevel++;
            while (count > 0) {

                Employee manager = queue.poll();
                manager.setLevel(employeeLevel);

                Set<Employee> reported = OrgRepository.managerMap.get(manager.getId());

                if (reported != null && !reported.isEmpty()) queue.addAll(reported);
                if (employeeLevel > Constants.LONG_LEVEL_THRESHOLD) {
                    result.add(manager);
                }

                count--;
            }
        }

        return result;
    }

    /**
     * prints all employee present in employee map
     */
    @Override
    public void printAllEmployee() {
        OrgRepository.employeeMap.forEach((key, employee) -> System.out.println(employee));
    }


}
