package org.swissre;

import org.swissre.entity.Employee;
import org.swissre.entity.ManagerEarning;
import org.swissre.repository.impl.CSVRepositoryImpl;
import org.swissre.repository.impl.OrgRepositoryImpl;
import org.swissre.service.OrgManagerService;
import org.swissre.service.impl.OrgManagerServiceImpl;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
Test Application for Swiss Re
 */
public class App 
{
    /**
     * main method to start the application
     * @param args command line arguments
     * @throws FileNotFoundException if file path does not exist
     */
    public static void main( String[] args ) throws FileNotFoundException {
        try
        {
            OrgManagerService orgManagerService = registerServices();
            App.run(orgManagerService);
        }
        catch(Exception exe)
        {
            System.out.println("Error Running the application" + Arrays.toString(exe.getStackTrace()));
        }

    }

    /**
     * Runs The application
     * @param orgManagerService Org Manager Service
     */
    private static void run(OrgManagerService orgManagerService) {
        System.out.println("All Employee List From CSV ");
        System.out.println("------------------------------------");
        orgManagerService.printAllEmployee();

        List<Employee> employeeList =  orgManagerService.getManagerEarning(ManagerEarning.MORE);
        System.out.println("------------------------------------");
        System.out.println("Manager with More Earning ");
        System.out.println("------------------------------------");
        employeeList.forEach(e -> System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getEarningMorePercentage()));

        employeeList =  orgManagerService.getManagerEarning(ManagerEarning.LESS);
        System.out.println("------------------------------------");
        System.out.println("Manager with Less Earning ");
        System.out.println("------------------------------------");
        employeeList.forEach(e -> System.out.println(e.getId() + " " + e.getFirstName()  + " " + e.getEarningLessPercentage()));

        employeeList =  orgManagerService.getEmployeesWithLongReporting();
        System.out.println("------------------------------------");
        System.out.println("Employees with Long Reporting ");
        System.out.println("------------------------------------");
        employeeList.forEach(e -> System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLevel()));
    }

    /**
     * Registers the implementation classes
     * @return OrgManagerService
     * @throws FileNotFoundException if file path is invalid or not found in CSVRepositoryImpl
     */
    private static OrgManagerService registerServices() throws FileNotFoundException {
        OrgManagerService orgManagerService = new OrgManagerServiceImpl(
                new OrgRepositoryImpl(
                        new CSVRepositoryImpl()));

        orgManagerService.bootStrapApplication();
        return orgManagerService;
    }


}
