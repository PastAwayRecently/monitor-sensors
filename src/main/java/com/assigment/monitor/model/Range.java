package com.assigment.monitor.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shmarlouski
 */
@Embeddable
@Data
@NoArgsConstructor
public class Range {

    @NotNull(message = "From is required")
    @Positive(message = "From must be a positive number")
    private Integer rangeFrom;

    @NotNull(message = "To is required")
    @Positive(message = "To must be a positive number")
    private Integer rangeTo;

    @PrePersist
    @PreUpdate
    public void validate() {
        if (rangeFrom > rangeTo) {
            throw new IllegalArgumentException("rangeFrom must be less than or equal to rangeTo");
        }
    }
}