package com.hms.service.test;

import com.hms.model.Procedure;
import com.hms.repository.ProcedureRepository;
import com.hms.service.ProcedureService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcedureServiceTest {

    @Mock
    private ProcedureRepository procedureRepository;

    @InjectMocks
    private ProcedureService procedureService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProcedure() {
        Procedure procedure = new Procedure(101, "MRI Scan", 2500.0);
        when(procedureRepository.save(procedure)).thenReturn(procedure);

        assertDoesNotThrow(() -> procedureService.saveProcedure(procedure));
        verify(procedureRepository, times(1)).save(procedure);
    }

    @Test
    public void testGetAllProcedures() {
        List<Procedure> procedures = List.of(
                new Procedure(101, "MRI Scan", 2500.0),
                new Procedure(102, "X-Ray", 500.0)
        );
        when(procedureRepository.findAll()).thenReturn(procedures);

        List<Procedure> result = procedureService.getAllProcedures();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProcedureById() {
        Procedure procedure = new Procedure(101, "MRI Scan", 2500.0);
        when(procedureRepository.findById(101)).thenReturn(Optional.of(procedure));

        Optional<Procedure> result = procedureService.getProcedureById(101);
        assertTrue(result.isPresent());
        assertEquals("MRI Scan", result.get().getName());
    }

    @Test
    public void testGetProcedureByName() {
        Procedure procedure = new Procedure(101, "MRI Scan", 2500.0);
        when(procedureRepository.findByName("MRI Scan")).thenReturn(Optional.of(procedure));

        Optional<Procedure> result = procedureService.getProcedureByName("MRI Scan");
        assertTrue(result.isPresent());
        assertEquals(2500.0, result.get().getCost());
    }

    @Test
    public void testUpdateCost() {
        Procedure procedure = new Procedure(101, "MRI Scan", 2500.0);
        when(procedureRepository.findById(101)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(any())).thenReturn(procedure);

        Optional<Procedure> result = procedureService.updateCost(101, 3000.0);
        assertTrue(result.isPresent());
        assertEquals(3000.0, result.get().getCost());
    }

    @Test
    public void testUpdateName() {
        Procedure procedure = new Procedure(101, "MRI Scan", 2500.0);
        when(procedureRepository.findById(101)).thenReturn(Optional.of(procedure));
        when(procedureRepository.save(any())).thenReturn(procedure);

        Optional<Procedure> result = procedureService.updateName(101, "Advanced MRI");
        assertTrue(result.isPresent());
        assertEquals("Advanced MRI", result.get().getName());
    }
}