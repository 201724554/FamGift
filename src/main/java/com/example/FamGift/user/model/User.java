package com.example.FamGift.user.model;

import com.example.FamGift.common.model.CommonEntity;
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
public class User extends CommonEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", length = 255)
    private String id;
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
}
