package com.hms.service.test;

import com.hms.model.Department;
import com.hms.model.TrainedIn;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.PhysicianRepository;
import com.hms.repository.TrainedInRepository;
import com.hms.service.DepartmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private PhysicianRepository physicianRepository;

    @Mock
    private TrainedInRepository trainedInRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartment() {
        Department department = new Department("Cardiology", 101);
        when(departmentRepository.save(department)).thenReturn(department);

        Department saved = departmentService.addDepartment(department);
        assertNotNull(saved);
        assertEquals("Cardiology", saved.getDeptName());
    }

    @Test
    public void testGetAllDepartments() {
        List<Department> departments = List.of(
                new Department("Cardiology", 101),
                new Department("Neurology", 102)
        );
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department("Cardiology", 101);
        department.setDeptId(1);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentById(1);
        assertTrue(result.isPresent());
        assertEquals("Cardiology", result.get().getDeptName());
    }

    @Test
    public void testUpdateDepartmentHead() {
        Department department = new Department("Cardiology", 101);
        department.setDeptId(1);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any())).thenReturn(department);

        Department updated = departmentService.updateDepartmentHead(1, 202);
        assertNotNull(updated);
        assertEquals(202, updated.getHead());
    }

    @Test
    public void testUpdateDepartmentName() {
        Department department = new Department("Cardiology", 101);
        department.setDeptId(1);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any())).thenReturn(department);

        Department updated = departmentService.updateDepartmentName(1, "Heart Care");
        assertNotNull(updated);
        assertEquals("Heart Care", updated.getDeptName());
    }
}