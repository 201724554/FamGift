package com.example.FamGift.user.model;

import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.group.model.Group;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_USER", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_EMAIL", columnNames = {"USER_EMAIL"}),
})
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_NAME", length = 20)
    private String name;
    @Column(name = "USER_EMAIL", length = 50)
    private String email;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_AUTHORITY", length = 10)
    @Enumerated(EnumType.STRING)
    private Auth authority;
    @Column(name = "USER_BIRTHDAY")
    private LocalDate birthday;
    @CreationTimestamp
    @Column(name = "USER_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "USER_UPDATED_DATE")
    private LocalDateTime updatedDate;
}
