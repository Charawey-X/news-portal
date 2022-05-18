package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DepartmentTest {
    private Department setUpNewDepartment() {
        return new Department("Business","Business News",5);
    }
    @Test
    public void newDepartment_InstantiatesCorrectly_true() throws Exception {
        Department testDepartment = setUpNewDepartment();
        assertTrue(true);
    }



    @Test
    public void newDepartment_getsName_String() throws Exception {
        Department testDepartment = setUpNewDepartment();
        assertEquals("Business",testDepartment.getName());
    }

    @Test
    public void getAge__getsDescription_sportsrelated() throws Exception {
        Department testDepartment = setUpNewDepartment();
        assertEquals("Business News", testDepartment.getDescription());
    }

    @Test
    public void getPower_getsNumberofemployees_String() throws Exception{
        Department testDepartment = setUpNewDepartment();
        assertEquals(5, testDepartment.getNumberOfEmployees());
    }

    @Test
    public void getId_DepartmentInstantiatesWithAnId_0() {
        Department newDepartment = setUpNewDepartment();
        assertEquals(0,newDepartment.getId());
    }

}