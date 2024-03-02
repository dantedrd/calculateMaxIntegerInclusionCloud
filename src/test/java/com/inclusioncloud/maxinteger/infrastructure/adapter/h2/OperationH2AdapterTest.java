package com.inclusioncloud.maxinteger.infrastructure.adapter.h2;

import com.inclusioncloud.maxinteger.config.exception.DataBaseException;
import com.inclusioncloud.maxinteger.domain.models.Operation;
import com.inclusioncloud.maxinteger.infrastructure.adapter.h2.models.OperationEntity;
import com.inclusioncloud.maxinteger.mocks.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationH2AdapterTest {
    @Mock
    private OperationH2Repository repository;

    @InjectMocks
    private OperationH2Adapter adapter;

    private Operation operationDomain;
    private OperationEntity operationEntity;

    @BeforeEach
    void setUp() {
        operationDomain = MockFactory.getOperationDomainFactory();
        operationEntity = OperationEntity.fromDomain(operationDomain);
    }

    /**
     * Test saving an operation to the database successfully.
     */
    @Test
    void save_Success() {
        when(repository.save(any(OperationEntity.class))).thenReturn(operationEntity);

        Operation result = adapter.save(operationDomain);

        assertNotNull(result, "Result should not be null");
        assertEquals(operationDomain.getX(), result.getX(), "X values should match");
        assertEquals(operationDomain.getY(), result.getY(), "Y values should match");
        assertEquals(operationDomain.getN(), result.getN(), "N values should match");
        assertEquals(operationDomain.getResult(), result.getResult(), "Result values should match");
    }

    /**
     * Test error handling when saving an operation to the database fails.
     */
    @Test
    void save_Error() {
        when(repository.save(any(OperationEntity.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(DataBaseException.class, () -> adapter.save(operationDomain),
                "DataBaseException should be thrown on save error");
    }

    /**
     * Test retrieving all operations from the database successfully.
     */
    @Test
    void getAll_Success() {
        when(repository.findAll()).thenReturn(List.of(operationEntity));

        List<Operation> result = adapter.getAll();

        assertNotNull(result, "Result list should not be null");
        assertFalse(result.isEmpty(), "Result list should not be empty");
        assertEquals(operationDomain.getX(), result.get(0).getX(), "X values should match");
        assertEquals(operationDomain.getY(), result.get(0).getY(), "Y values should match");
        assertEquals(operationDomain.getN(), result.get(0).getN(), "N values should match");
        assertEquals(operationDomain.getResult(), result.get(0).getResult(), "Result values should match");
    }

    /**
     * Test error handling when retrieving all operations from the database fails.
     */
    @Test
    void getAll_Error() {
        when(repository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(DataBaseException.class, () -> adapter.getAll(),
                "DataBaseException should be thrown on getAll error");
    }
}