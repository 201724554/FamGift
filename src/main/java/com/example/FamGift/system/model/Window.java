package com.example.FamGift.system.model;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Window {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WINDOW_ID")
    private Long id;
    @Column(name = "WINDOW_PATH")
    private String path;
    @CreationTimestamp
    @Column(name = "WINDOW_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "WINDOW_UPDATED_DATE")
    private LocalDateTime updatedDate;
}
