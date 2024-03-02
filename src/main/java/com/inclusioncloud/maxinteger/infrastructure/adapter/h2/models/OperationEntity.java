package com.inclusioncloud.maxinteger.infrastructure.adapter.h2.models;

import com.inclusioncloud.maxinteger.domain.models.Operation;
import jakarta.persistence.*;
import lombok.*;


/**
 * Entity class representing an operation record in the database.
 * Maps the 'operations' table columns to fields in this class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "operations")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long x;

    @Column(nullable = false)
    private Long y;

    @Column(nullable = false)
    private Long n;

    @Column(nullable = false)
    private Long result;

    /**
     * Converts a {@link Operation} object to an {@link OperationEntity}.
     *
     * @param domain The OperationDomain object to convert.
     * @return The corresponding OperationEntity object.
     */
    public static OperationEntity fromDomain(Operation domain) {
        return OperationEntity.builder()
                .x(domain.getX())
                .y(domain.getY())
                .n(domain.getN())
                .result(domain.getResult())
                .build();
    }

    /**
     * Converts this entity to its domain representation.
     *
     * @return The corresponding OperationDomain object.
     */
    public Operation toDomain() {
        return new Operation(this.x, this.y, this.n, this.result);
    }
}
