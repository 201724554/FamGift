package com.example.FamGift.common.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class CommonEntity {
    @CreationTimestamp
    @Column(name = "CREATE_DATE")
    private LocalDateTime insertedLocalDateTime;
    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updatedLocalDateTime;
}
