package org.swissre.entity;

import java.math.BigDecimal;

public class Employee  implements Comparable<Employee> {
    private String id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private BigDecimal earningMorePercentage;
    private BigDecimal earningLessPercentage;
    private Integer level;
    private String managerId;
    private boolean isCEO;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getEarningMorePercentage() {
        return earningMorePercentage;
    }

    public void setEarningMorePercentage(BigDecimal earningMorePercentage) {
        this.earningMorePercentage = earningMorePercentage;
    }

    public BigDecimal getEarningLessPercentage() {
        return earningLessPercentage;
    }

    public void setEarningLessPercentage(BigDecimal earningLessPercentage) {
        this.earningLessPercentage = earningLessPercentage;
    }

    public boolean isCEO() {
        return isCEO;
    }

    public void setCEO(boolean CEO) {
        isCEO = CEO;
    }

    public Employee() {
    }

    public Employee(String id, String firstName, String lastName, BigDecimal salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", earningMorePercentage=" + earningMorePercentage +
                ", earningLessPercentage=" + earningLessPercentage +
                ", level=" + level +
                ", managerId='" + managerId + '\'' +
                ", isCEO=" + isCEO +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.salary.compareTo(o.salary);
    }
}
