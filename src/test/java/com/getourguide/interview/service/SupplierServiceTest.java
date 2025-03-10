package com.getourguide.interview.service;

import com.getourguide.interview.dto.SupplierDto;
import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SupplierServiceTest {
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;

    @BeforeEach
    void setup() {
        this.supplierRepository = mock(SupplierRepository.class);
        this.supplierService = new SupplierService(supplierRepository);
    }

    @Test
    void testGetSuppliers() {
        // Arrange
        Supplier testSupplier = new Supplier();
        testSupplier.setId(1L);
        testSupplier.setName("Test Supplier");
        testSupplier.setAddress("123 Test St");
        testSupplier.setZip("12345");
        testSupplier.setCity("Test City");
        testSupplier.setCountry("Test Country");

        when(supplierRepository.findAll()).thenReturn(List.of(testSupplier));

        // Act
        List<SupplierDto> result = supplierService.getSuppliers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        SupplierDto dto = result.getFirst();
        assertEquals(1L, dto.getId());
        assertEquals("Test Supplier", dto.getName());
        assertEquals("123 Test St", dto.getAddress());
        assertEquals("12345", dto.getZip());
        assertEquals("Test City", dto.getCity());
        assertEquals("Test Country", dto.getCountry());
    }

    @Test
    void testSearchSuppliers() {
        // Arrange
        Supplier matchingSupplier = new Supplier();
        matchingSupplier.setId(1L);
        matchingSupplier.setName("Test Supplier");
        matchingSupplier.setAddress("123 Test St");
        matchingSupplier.setZip("12345");
        matchingSupplier.setCity("Test City");
        matchingSupplier.setCountry("Test Country");

        Supplier nonMatchingSupplier = new Supplier();
        nonMatchingSupplier.setId(2L);
        nonMatchingSupplier.setName("Other Supplier");
        nonMatchingSupplier.setAddress("456 Other St");
        nonMatchingSupplier.setZip("67890");
        nonMatchingSupplier.setCity("Other City");
        nonMatchingSupplier.setCountry("Other Country");

        when(supplierRepository.findAll()).thenReturn(List.of(matchingSupplier, nonMatchingSupplier));

        // Act
        List<SupplierDto> result = supplierService.searchSuppliers("Test");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        SupplierDto dto = result.getFirst();
        assertEquals(1L, dto.getId());
        assertEquals("Test Supplier", dto.getName());
        assertEquals("123 Test St", dto.getAddress());
        assertEquals("12345", dto.getZip());
        assertEquals("Test City", dto.getCity());
        assertEquals("Test Country", dto.getCountry());
    }

    @Test
    void testSearchSuppliersNoMatch() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Test Supplier");

        when(supplierRepository.findAll()).thenReturn(List.of(supplier));

        // Act
        List<SupplierDto> result = supplierService.searchSuppliers("NonExistent");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}