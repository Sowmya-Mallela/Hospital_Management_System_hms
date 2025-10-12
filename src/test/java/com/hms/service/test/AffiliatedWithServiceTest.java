package com.hms.service.test;

import com.hms.model.AffiliatedWith;
import com.hms.model.AffiliatedWithId;
import com.hms.repository.AffiliatedWithRepository;
import com.hms.service.AffiliatedWithService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AffiliatedWithServiceTest {

    @Mock
    private AffiliatedWithRepository affiliatedWithRepository;

    @InjectMocks
    private AffiliatedWithService affiliatedWithService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPhysiciansByDepartment() {
        List<AffiliatedWith> affiliations = List.of(new AffiliatedWith());
        when(affiliatedWithRepository.findByIdDepartment(1)).thenReturn(affiliations);

        List<AffiliatedWith> result = affiliatedWithService.getPhysiciansByDepartment(1);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetDepartmentsByPhysician() {
        List<AffiliatedWith> affiliations = List.of(new AffiliatedWith());
        when(affiliatedWithRepository.findByIdPhysician(101)).thenReturn(affiliations);

        List<AffiliatedWith> result = affiliatedWithService.getDepartmentsByPhysician(101);
        assertEquals(1, result.size());
    }

    @Test
    public void testCountPhysiciansInDepartment() {
        when(affiliatedWithRepository.countByIdDepartment(1)).thenReturn(5L);

        long count = affiliatedWithService.countPhysiciansInDepartment(1);
        assertEquals(5L, count);
    }

    @Test
    public void testHasPrimaryAffiliation_True() {
        AffiliatedWith aff = new AffiliatedWith(new AffiliatedWithId(101, 1), true);
        when(affiliatedWithRepository.findFirstByIdPhysicianAndPrimaryAffiliationTrue(101)).thenReturn(aff);

        boolean result = affiliatedWithService.hasPrimaryAffiliation(101);
        assertTrue(result);
    }

    @Test
    public void testHasPrimaryAffiliation_False() {
        when(affiliatedWithRepository.findFirstByIdPhysicianAndPrimaryAffiliationTrue(101)).thenReturn(null);

        boolean result = affiliatedWithService.hasPrimaryAffiliation(101);
        assertFalse(result);
    }

    @Test
    public void testUpdatePrimaryAffiliation_Success() {
        AffiliatedWith aff1 = new AffiliatedWith(new AffiliatedWithId(101, 1), true);
        AffiliatedWith aff2 = new AffiliatedWith(new AffiliatedWithId(101, 2), false);
        List<AffiliatedWith> affiliations = List.of(aff1, aff2);

        when(affiliatedWithRepository.findByIdPhysician(101)).thenReturn(affiliations);

        boolean result = affiliatedWithService.updatePrimaryAffiliation(101);
        assertTrue(result);
        verify(affiliatedWithRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testUpdatePrimaryAffiliation_Failure() {
        when(affiliatedWithRepository.findByIdPhysician(101)).thenReturn(Collections.emptyList());

        boolean result = affiliatedWithService.updatePrimaryAffiliation(101);
        assertFalse(result);
        verify(affiliatedWithRepository, never()).saveAll(anyList());
    }

    @Test
    public void testAddAffiliation() {
        AffiliatedWith aff = new AffiliatedWith(new AffiliatedWithId(101, 1), true);
        when(affiliatedWithRepository.save(aff)).thenReturn(aff);

        String result = affiliatedWithService.addAffiliation(aff);
        assertEquals("Record Created Successfully", result);
    }
}