package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private Employee setUpNewEmployee() {
        return new Employee("john","CEO",5);
    }

    @Test
    public void newUserInstantiatesCorrectly_true() throws Exception {
        Employee testUser = setUpNewEmployee();
        assertTrue(true);
    }



    @Test
    public void getsNameOfNewEmployee() throws Exception {
        Employee testUser = setUpNewEmployee();
        assertEquals("john",testUser.getName());
    }

    @Test
    public void getsRoleOfEmployee() throws Exception {
        Employee testUser = setUpNewEmployee();
        assertEquals("CEO", testUser.getRole());
    }

    @Test
    public void getsNumberOfEmployees() throws Exception{
        Employee testUser = setUpNewEmployee();
        assertEquals(5, testUser.getDepartmentId());
    }

    @Test
    public void employeeInstantiatesWithAnId_0() {
        Employee testUser = setUpNewEmployee();
        assertEquals(0,testUser.getId());
    }
}
