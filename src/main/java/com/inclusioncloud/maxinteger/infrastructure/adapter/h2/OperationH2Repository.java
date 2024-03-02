package com.inclusioncloud.maxinteger.infrastructure.adapter.h2;


import com.inclusioncloud.maxinteger.infrastructure.adapter.h2.models.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link OperationEntity} instances.
 * The repository is used for standard data access operations on Operation entities.
 */
@Repository
public interface OperationH2Repository extends JpaRepository<OperationEntity, Long> {
    // In the future, if you need to implement custom queries, they can be added here.
}
