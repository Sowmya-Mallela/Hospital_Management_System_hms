package com.hms.service.test;

import com.hms.model.Nurse;
import com.hms.repository.NurseRepository;
import com.hms.service.NurseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NurseServiceTest {

    @Mock
    private NurseRepository nurseRepository;

    @InjectMocks
    private NurseService nurseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNurse() {
        Nurse nurse = new Nurse(101, "Sowmya", "Senior Nurse", true, 123456789);
        when(nurseRepository.save(nurse)).thenReturn(nurse);
        Nurse saved = nurseService.addNurse(nurse);
        assertNotNull(saved);
        assertEquals("Sowmya", saved.getName());
    }

    @Test
    public void testGetAllNurses() {
        List<Nurse> nurses = List.of(
                new Nurse(101, "Sowmya", "Senior Nurse", true, 123456789),
                new Nurse(102, "Lakshmi", "Junior Nurse", false, 987654321)
        );
        when(nurseRepository.findAll()).thenReturn(nurses);

        List<Nurse> result = nurseService.getAllNurses();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetNurseById() {
        Nurse nurse = new Nurse(101, "Sowmya", "Senior Nurse", true, 123456789);
        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurse));

        Optional<Nurse> result = nurseService.getNurseById(101);
        assertTrue(result.isPresent());
        assertEquals("Sowmya", result.get().getName());
    }

    @Test
    public void testUpdateRegistered() {
        Nurse nurse = new Nurse(101, "Sowmya", "Senior Nurse", false, 123456789);
        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurse));
        when(nurseRepository.save(any())).thenReturn(nurse);

        Nurse updated = nurseService.updateRegistered(101, true);
        assertNotNull(updated);
        assertTrue(updated.getRegistered());
    }

    @Test
    public void testUpdateSsn() {
        Nurse nurse = new Nurse(101, "Sowmya", "Senior Nurse", true, 123456789);
        when(nurseRepository.findById(101)).thenReturn(Optional.of(nurse));
        when(nurseRepository.save(any())).thenReturn(nurse);

        Nurse updated = nurseService.updateSsn(101, 987654321);
        assertNotNull(updated);
        assertEquals(987654321, updated.getSsn());
    }
}