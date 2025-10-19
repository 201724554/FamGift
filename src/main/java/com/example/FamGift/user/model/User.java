package com.example.FamGift.user.model;

import com.example.FamGift.common.model.CommonEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_USER", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_EMAIL", columnNames = {"USER_EMAIL"}),
})
public class User extends CommonEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 카카오 로그인 api에서 리턴한 id를 사용해서 auto increment 사용 X
    @Column(name = "USER_ID")
    @Getter
    private Long id;

    @Column(name = "USER_NAME", length = 20)
    @Getter
    private String name;

    @Column(name = "USER_EMAIL", length = 50)
    private String email;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_AUTHORITY", length = 10)
    @Getter
    @Enumerated(EnumType.STRING)
    private Auth authority;

    @Column(name = "USER_BIRTHDAY")
    private LocalDate birthday;

    public User() {}

    public User(Long id, String name, String password, Auth authority) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authority = authority;
    }

    public User(Long id, String name, String password, String authority) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authority = Auth.valueOf(authority);
    }

    public boolean isEqualTo(User user) {
        return Objects.equals(this.id, user.getId());
    }
}
